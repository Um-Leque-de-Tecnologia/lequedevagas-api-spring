package br.edu.faculdade.vagas;

// =============================================================================
// AULA 03 · a frente 4 estreia o @RequestParam
//
// Este arquivo inteiro é da aula 03. Na aula 02 a frente 4 entregou só o
// EstatisticasService, que continua ativo ali do lado.
//
// Para ativar: descomente tudo abaixo (o import junto).
// =============================================================================
//
// import org.springframework.stereotype.Service;
//
// import java.util.List;
//
// /**
//  * A frente 4 é a única sem recurso próprio: ela consulta o que a frente 1
//  * guarda — e é por isso que a equipe precisou combinar a assinatura de
//  * VagaRepository.todas() antes de qualquer pessoa abrir a IDE.
//  */
// @Service
// public class BuscaService {
//
//     // O repositorio da frente 1, e não o service dela — igual ao que o
//     // EstatisticasService já fazia na aula 02. Buscar é uma regra, e regra
//     // não chama regra por preguiça. No dia em que VagaService.listar() passar
//     // a esconder vagas expiradas, a busca não deve mudar junto sem alguém
//     // decidir isso.
//     private final VagaRepository repositorio;
//
//     public BuscaService(VagaRepository repositorio) {
//         this.repositorio = repositorio;
//     }
//
//     // Boolean, e não boolean: só o objeto pode ser null, e null é exatamente
//     // como "o filtro não foi enviado" chega aqui.
//     public List<Vaga> buscar(String area, String senioridade,
//                              Boolean aceitaIniciante) {
//         return repositorio.todas().stream()
//             .filter(v -> area == null
//                       || v.area().equalsIgnoreCase(area))
//             .filter(v -> senioridade == null
//                       || v.senioridade().equalsIgnoreCase(senioridade))
//             .filter(v -> aceitaIniciante == null
//                       || v.aceitaIniciante() == aceitaIniciante)
//             .toList();
//     }
// }
