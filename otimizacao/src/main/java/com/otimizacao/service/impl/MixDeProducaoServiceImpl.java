package com.otimizacao.service.impl;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;

import com.otimizacao.domain.MixDeProducao;
import com.otimizacao.service.MixDeProducaoService;
import org.gnu.glpk.GLPK;
import org.gnu.glpk.GLPKConstants;
import org.gnu.glpk.GlpkException;
import org.gnu.glpk.SWIGTYPE_p_double;
import org.gnu.glpk.SWIGTYPE_p_int;
import org.gnu.glpk.glp_prob;
import org.gnu.glpk.glp_smcp;

@EnableCaching
@Service
public class MixDeProducaoServiceImpl implements MixDeProducaoService {

	@Override
	public String resolveSimplex(MixDeProducao mix) {
		String retorno = null;
		glp_prob problema;
		glp_smcp parm;
		SWIGTYPE_p_int ind;
		SWIGTYPE_p_double val;
		int ret;
		try {

			// Criando o problema
			problema = GLPK.glp_create_prob();
			GLPK.glp_set_prob_name(problema, "Problema de Mix de Produção");

			// Definindo as colunas
			GLPK.glp_add_cols(problema, mix.getFuncaoObjetiva().getX().size());
			for (int i = 0; i < mix.getFuncaoObjetiva().getX().size(); i++) {
				GLPK.glp_set_col_name(problema, i + 1, "x" + i + 1);
				GLPK.glp_set_col_kind(problema, i + 1, GLPKConstants.GLP_IV);
				GLPK.glp_set_col_bnds(problema, i + 1, GLPKConstants.GLP_LO, 0, 0);

			}

			// Definindo as restrições
			GLPK.glp_add_rows(problema, mix.getRestricoes().size());

			for (int i = 0; i < mix.getFuncaoObjetiva().getX().size(); i++) {
				GLPK.glp_set_row_name(problema, i + 1, "restricao" + (i + 1));

				// Verifica se o tipo de restrição escolhido foi o menor ou maior
				switch (mix.getRestricoes().get(i).getTipoRestricao()) {
				case "menor":
					GLPK.glp_set_row_bnds(problema, i + 1, GLPKConstants.GLP_UP, 0,
							mix.getRestricoes().get(i).getDisponibilidade());
					break;
				case "maior":
					GLPK.glp_set_row_bnds(problema, i + 1, GLPKConstants.GLP_LO,
							mix.getRestricoes().get(i).getDisponibilidade(), 0);
					break;
				default:
					break;
				}

				GLPK.glp_set_row_bnds(problema, i + 1, GLPKConstants.GLP_DB, 0,
						mix.getRestricoes().get(i).getDisponibilidade());

				ind = GLPK.new_intArray(mix.getFuncaoObjetiva().getX().size() + 1);
				val = GLPK.new_doubleArray(mix.getFuncaoObjetiva().getX().size() + 1);

				for (int j = 0; j < mix.getFuncaoObjetiva().getX().size(); j++) {
					GLPK.intArray_setitem(ind, (j + 1), (j + 1));
					GLPK.doubleArray_setitem(val, (j + 1), mix.getRestricoes().get(j).getX().get(i));
				}
				GLPK.glp_set_mat_row(problema, (i + 1), 2, ind, val);
			}

			// Definindo objetivo
			GLPK.glp_set_obj_name(problema, "Z");
			if (mix.getFuncaoObjetiva().getObjetivo().equals("MIN")) {
				GLPK.glp_set_obj_dir(problema, GLPKConstants.GLP_MIN);
			} else {
				GLPK.glp_set_obj_dir(problema, GLPKConstants.GLP_MAX);
			}

			for (int j = 0; j < mix.getFuncaoObjetiva().getX().size(); j++) {
				GLPK.glp_set_obj_coef(problema, (j + 1), mix.getFuncaoObjetiva().getX().get(j));
			}

			// Manda resolver
			parm = new glp_smcp();
			GLPK.glp_init_smcp(parm);
			ret = GLPK.glp_simplex(problema, parm);

			// Retorno da solução
			if (ret == 0) {
				retorno = write_lp_solution(problema);
			} else {
				retorno = "Não há solução ótima para o problema";
			}
		} catch (GlpkException ex) {
			ex.getMessage();
		}

		return retorno;
	}

	/**
	 * write simplex solution
	 * 
	 * @param lp
	 *            problem
	 */
	static String write_lp_solution(glp_prob problema) {
		String resposta = "";
		int i;
		int n;
		String name;
		double val;
		name = GLPK.glp_get_obj_name(problema);
		val = GLPK.glp_get_obj_val(problema);
		resposta += name + " = " + val;
		n = GLPK.glp_get_num_cols(problema);
		for (i = 1; i <= n; i++) {
			name = GLPK.glp_get_col_name(problema, i);
			val = GLPK.glp_get_col_prim(problema, i);
			resposta += name + " = " + val;
		}

		return resposta;
	}
}