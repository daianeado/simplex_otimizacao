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
		MixDeProducao resultado = new MixDeProducao();
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
			 GLPK.glp_add_cols(problema, mix.getQuantidadeVariaveisDecisao());
			 for(int i = 0; i< mix.getQuantidadeVariaveisDecisao(); i++ ) {
				 GLPK.glp_set_col_name(problema, i+1, "x"+i);
				 GLPK.glp_set_col_kind(problema, i+1, GLPKConstants.GLP_IV);
				 GLPK.glp_set_col_bnds(problema, i+1, GLPKConstants.GLP_UP, 0, 0);
			 }
			 
			 //Definindo as linhas
			 GLPK.glp_add_rows(problema, mix.getQuantidadeRestricao());
			 for(int j = 0; j< mix.getQuantidadeRestricao(); j++) {
				 GLPK.glp_set_row_name(problema, j+1, "c"+j);
				 GLPK.glp_set_row_bnds(problema, j+1, GLPKConstants.GLP_UP, 0, mix.getRestricoes().get(j).getResult());
				 
				 ind = GLPK.new_intArray(mix.getQuantidadeVariaveisDecisao()+1);
				 val = GLPK.new_doubleArray(mix.getQuantidadeVariaveisDecisao()+1);
				 
				 for(int k = 0; k < mix.getQuantidadeVariaveisDecisao(); k++) {
					 GLPK.intArray_setitem(ind, (k+1), (k+1));
		             GLPK.doubleArray_setitem(val, (k+1), (k+1));
				 }
				 GLPK.glp_set_mat_row(problema, (j+1), mix.getQuantidadeVariaveisDecisao(), ind, val);
			 }
			 
			 //Definindo objetivo
			 GLPK.glp_set_obj_name(problema, "Z");
			 if(mix.getFuncaoObjetiva().getObjetivo().equals("MIN")) {
				 GLPK.glp_set_obj_dir(problema, GLPKConstants.GLP_MIN);				 
			 } else {
				 GLPK.glp_set_obj_dir(problema, GLPKConstants.GLP_MAX);	
			 }
			 
			 for(int i = 0; i< mix.getQuantidadeVariaveisDecisao(); i++) {
				 GLPK.glp_set_obj_coef(problema, i, mix.getFuncaoObjetiva().getX().get(i));
			 }
			
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