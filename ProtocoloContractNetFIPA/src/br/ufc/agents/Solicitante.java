package br.ufc.agents;


import br.ufc.behaviours.BuscaB;
import br.ufc.behaviours.SolicitanteB;
import jade.core.Agent;
import jade.domain.FIPAAgentManagement.ServiceDescription;

public class Solicitante extends Agent {

	/**
	 * @author marcosf
	 */
	private static final long serialVersionUID = 1L;
	private SolicitanteB behav;
	private BuscaB buscab;

	@Override
	protected void setup() {
		behav = new SolicitanteB(this);
		// Captura argumentos
		Object[] args = getArguments();
		
		if (args != null && args.length > 0) {
			String argumento = (String) args[0];
			addBehaviour(behav);
			// Se o argumento é um celular
			if (argumento.equalsIgnoreCase("Celular")) {
				ServiceDescription servico = new ServiceDescription();
				servico.setType("venda celular");
				// busca por um agente que fornça o serviço
				busca(servico, "celular");
			}

			if (argumento.equalsIgnoreCase("Geladeira")) {
				ServiceDescription servico = new ServiceDescription();
				servico.setType("venda geladeira");
				// busca por um agente que forneça o serviço
				busca(servico, "geladeira");

			}
			if (argumento.equalsIgnoreCase("televisão")) {
				ServiceDescription servico = new ServiceDescription();
				servico.setType("venda televisão");
				// busca por um agente que forneça o serviço
				busca(servico, "televisão");
			}

		}

	}

	// Método para realizar a busca
	protected void busca(final ServiceDescription servico, final String pedido) {

		// A cada minuto tenta buscar por agentes que forneçam o serviços
		buscab = new BuscaB(this, 50);
		buscab.setService(servico);
		buscab.setPedido(pedido);
		addBehaviour(buscab);

	}

}
