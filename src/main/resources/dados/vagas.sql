-- Frente 1 · as 12 vagas. Doze vagas, quatro áreas, três senioridades, sete
-- aceitando iniciante: é isso que faz /estatisticas e a busca mostrarem
-- diferença. Lista uniforme esconde bug de filtro.
--
-- Sem a coluna id: quem numera é o banco, na ordem dos INSERT, e a primeira
-- vaga continua sendo a /vagas/1. Escrever o id à mão aqui faz o primeiro POST
-- colidir com a vaga 1.
INSERT INTO vaga (titulo, descricao, area, senioridade, local, aceita_iniciante, empresa_slug) VALUES
  ('Pessoa Desenvolvedora Front-end Júnior', 'Telas do produto em React e Next.js, pareando com gente mais experiente.', 'Front-end', 'Júnior', 'Remoto', TRUE, 'aurora-tech'),
  ('Estágio em Front-end', 'Primeiro contato com HTML, CSS e JavaScript num time de agência.', 'Front-end', 'Estágio', 'Presencial · Olinda', TRUE, 'mareh-digital'),
  ('Pessoa Desenvolvedora Front-end Pleno', 'Dono de features inteiras, do desenho à publicação.', 'Front-end', 'Pleno', 'Remoto', FALSE, 'nuvem-rosa'),
  ('Pessoa Desenvolvedora React Júnior', 'Interface da plataforma de cursos, com foco em acessibilidade.', 'Front-end', 'Júnior', 'Híbrido · Recife', TRUE, 'coral-labs'),
  ('Pessoa Desenvolvedora Java Júnior', 'APIs REST com Spring Boot, integrando transportadoras.', 'Back-end', 'Júnior', 'Remoto', TRUE, 'tucano-systems'),
  ('Pessoa Desenvolvedora Back-end Pleno', 'Serviços de agendamento e faturamento, com fila e cache.', 'Back-end', 'Pleno', 'Remoto', FALSE, 'aurora-tech'),
  ('Pessoa Desenvolvedora Node Pleno', 'Serviço de trilhas e progresso, em TypeScript.', 'Back-end', 'Pleno', 'Híbrido · Recife', FALSE, 'coral-labs'),
  ('Analista de Dados Júnior', 'SQL, planilhas e construção de painéis para clientes do varejo.', 'Dados', 'Júnior', 'Híbrido · Recife', TRUE, 'nuvem-rosa'),
  ('Estágio em Dados', 'Limpeza de base e primeiros painéis, com acompanhamento semanal.', 'Dados', 'Estágio', 'Remoto', TRUE, 'nuvem-rosa'),
  ('Pessoa Engenheira de Dados Pleno', 'Pipelines de rastreamento de carga, em Python e SQL.', 'Dados', 'Pleno', 'Remoto', FALSE, 'tucano-systems'),
  ('Pessoa Desenvolvedora Mobile Júnior', 'Aplicativos em React Native para clientes da agência.', 'Mobile', 'Júnior', 'Remoto', TRUE, 'mareh-digital'),
  ('Pessoa Desenvolvedora Android Júnior', 'Aplicativo de agenda para as clínicas parceiras.', 'Mobile', 'Júnior', 'Presencial · Recife', FALSE, 'aurora-tech');
