//package br.ufc.agents;
//
//import br.ufc.common.Operacoes;
//import jade.core.Agent;
//import jade.domain.FIPAAgentManagement.ServiceDescription;
//
//public class VendedorDeTelevisao extends Agent {
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
//		// Seu serviço eh vender televisões
//		servico.setType("venda televisão");
//		servico.setName(this.getLocalName());
//		ope.registraServico(servico, this);
//		ope.recebeMensagens("televisão", "Olá tudo bem? Estou vendendo uma Televisão LG!", this);
//		
//	}
//}
