# Changelog

Todas as mudanças notáveis neste projeto serão documentadas aqui.
Formato: https://keepachangelog.com

## [1.0.0] - 2026-05-16

### Added

- RF01: Listagem de receitas com cards responsivos (nome, categoria, tempo de preparo) (#1).
- RF02: Filtro de busca em tempo real case-insensitive (#4).
- RF03: Formulário de cadastro com Reactive Forms e validações Bean Validation (#3).
- RF04: Tela de visualização de detalhes com preservação de quebras de linha via CSS (#7).
- RF05: Funcionalidade de remoção de receitas com confirmação nativa (#9).
- CI: Pipeline do GitHub Actions configurado e executando build completo de backend e frontend.

### Technical

- Entidade Recipe com validações Bean Validation (@Valid)
- Branch protection configurado no main (PR + CI obrigatórios)
- Conventional Commits adotado como padrão do projeto

## [0.1.0] - 2026-05-15

### Added

- Configuração inicial do repositório (monorepo)
- Estrutura /backend e /frontend
- README.md e CHANGELOG.md iniciais
- Branch develop configurado
