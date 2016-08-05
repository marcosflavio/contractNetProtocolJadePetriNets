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
			// Se o argumento � um celular
			if (argumento.equalsIgnoreCase("Celular")) {
				ServiceDescription servico = new ServiceDescription();
				servico.setType("venda celular");
				// busca por um agente que forn�a o servi�o
				busca(servico, "celular");
			}

			if (argumento.equalsIgnoreCase("Geladeira")) {
				ServiceDescription servico = new ServiceDescription();
				servico.setType("venda geladeira");
				// busca por um agente que forne�a o servi�o
				busca(servico, "geladeira");

			}
			if (argumento.equalsIgnoreCase("televis�o")) {
				ServiceDescription servico = new ServiceDescription();
				servico.setType("venda televis�o");
				// busca por um agente que forne�a o servi�o
				busca(servico, "televis�o");
			}

		}

	}

	// M�todo para realizar a busca
	protected void busca(final ServiceDescription servico, final String pedido) {

		// A cada minuto tenta buscar por agentes que forne�am o servi�os
		buscab = new BuscaB(this, 50);
		buscab.setService(servico);
		buscab.setPedido(pedido);
		addBehaviour(buscab);

	}

}
