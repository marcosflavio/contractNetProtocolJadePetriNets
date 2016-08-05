//package br.ufc.agents;
//
//import br.ufc.common.Operacoes;
//import jade.core.Agent;
//import jade.domain.FIPAAgentManagement.ServiceDescription;
//
//public class VendedorDeGeladeira extends Agent{
//
//	/**
//	 * @author marcosf
//	 */
//	private static final long serialVersionUID = 1L;
//	private Operacoes ope;
//	
//	@Override
//	protected void setup() {
//		
//		ope = new Operacoes();
//		// Descrição do serviço
//		ServiceDescription servico = new ServiceDescription();
//		// Seu serviço eh vender geladeiras
//		servico.setType("venda geladeira");
//		servico.setName(this.getLocalName());
//		ope.registraServico(servico, this);
//		ope.recebeMensagens("geladeira", "Olá, me chamo Francisco e estou vendendo uma Geladeira Consul!", this);
//		
//	}
//}
