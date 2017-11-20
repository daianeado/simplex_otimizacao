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
			
			//Criando o problema
			problema = GLPK.glp_create_prob();
			GLPK.glp_set_prob_name(problema, "Problema de Mix de Produção");
			
			//Definindo as colunas
			GLPK.glp_add_cols(problema, 2);
			GLPK.glp_set_col_name(problema, 1, "x1");
			GLPK.glp_set_col_kind(problema, 1, GLPKConstants.GLP_CV);
			GLPK.glp_set_col_bnds(problema, 1, GLPKConstants.GLP_LO, 0, 0);
			
			GLPK.glp_set_col_name(problema, 2, "x2");
			GLPK.glp_set_col_kind(problema, 2, GLPKConstants.GLP_CV);
			GLPK.glp_set_col_bnds(problema, 2, GLPKConstants.GLP_LO, 0, 0);
			 
			 //Definindo as linhas
			
			GLPK.glp_add_rows(problema, 3);
			//Linha1
			GLPK.glp_set_row_name(problema, 1, "c1");
			GLPK.glp_set_row_bnds(problema, 1, GLPKConstants.GLP_UP, 0, mix.getRestricoes().get(0).getResult());

			ind = GLPK.new_intArray(3);
			GLPK.intArray_setitem(ind, 1, 1);
			GLPK.intArray_setitem(ind, 2, 2);
			
			val = GLPK.new_doubleArray(3);
			GLPK.doubleArray_setitem(val, 1,  mix.getRestricoes().get(0).getX1());
			GLPK.doubleArray_setitem(val, 2,  mix.getRestricoes().get(0).getX2());

			GLPK.glp_set_mat_row(problema, 1, 2, ind, val);
			
			//Linha2
			GLPK.glp_set_row_name(problema, 2, "c2");
			GLPK.glp_set_row_bnds(problema, 2, GLPKConstants.GLP_UP, 0, mix.getRestricoes().get(1).getResult());

			ind = GLPK.new_intArray(3);
			GLPK.intArray_setitem(ind, 1, 1);
			GLPK.intArray_setitem(ind, 2, 2);
			
			val = GLPK.new_doubleArray(3);
			GLPK.doubleArray_setitem(val, 1,  mix.getRestricoes().get(1).getX1());
			GLPK.doubleArray_setitem(val, 2,  mix.getRestricoes().get(1).getX2());

			GLPK.glp_set_mat_row(problema, 2, 2, ind, val);
		
			//Linha3
			GLPK.glp_set_row_name(problema, 3, "c3");
			GLPK.glp_set_row_bnds(problema, 3, GLPKConstants.GLP_UP, 0, mix.getRestricoes().get(2).getResult());

			ind = GLPK.new_intArray(3);
			GLPK.intArray_setitem(ind, 1, 1);
			GLPK.intArray_setitem(ind, 2, 2);
			
			val = GLPK.new_doubleArray(3);
			GLPK.doubleArray_setitem(val, 1,  mix.getRestricoes().get(2).getX1());
			GLPK.doubleArray_setitem(val, 2,  mix.getRestricoes().get(2).getX2());

			GLPK.glp_set_mat_row(problema, 3, 2, ind, val);
			 
			 //Definindo objetivo
			 GLPK.glp_set_obj_name(problema, "Z");
			 if(mix.getFuncaoObjetiva().getObjetivo().equals("MIN")) {
				 GLPK.glp_set_obj_dir(problema, GLPKConstants.GLP_MIN);				 
			 } else {
				 GLPK.glp_set_obj_dir(problema, GLPKConstants.GLP_MAX);	
			 }
			 
			 GLPK.glp_set_obj_coef(problema, 0, 0);
			 GLPK.glp_set_obj_coef(problema, 1, mix.getFuncaoObjetiva().getX1());
			 GLPK.glp_set_obj_coef(problema, 2, mix.getFuncaoObjetiva().getX2());
			
			
			//Manda resolver
			 parm = new glp_smcp();
			 GLPK.glp_init_smcp(parm);
			 ret = GLPK.glp_simplex(problema, parm);
			 
			 // Retrieve solution
			 if (ret == 0) {
				 retorno =  write_lp_solution(problema);
			 } else {
			   	retorno =  "Não há solução ótima para o problema";
			 }
		}catch (GlpkException ex) {
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