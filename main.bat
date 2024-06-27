@echo off

REM Navega até o diretório onde está o docker-compose.yml
cd /d style-me-back-end

REM Verifica se docker-compose.yml existe
echo Verificando se docker-compose.yml existe...
if not exist docker-compose.yml (
    echo docker-compose.yml não encontrado no diretório %cd%.
    pause
    exit /b 1
)
echo docker-compose.yml encontrado, executando docker-compose up --build...

REM Executa o comando docker-compose up --build
docker-compose up --build

REM Pausa para garantir que a janela não feche automaticamente
echo Processo concluído com sucesso. Pressione qualquer tecla para sair...
pause
