# Użycie oficjalnego obrazu Ubuntu jako podstawy
FROM ubuntu:latest

# Ustawienie zmiennych środowiskowych, aby uniknąć interaktywnych pytań podczas instalacji
ENV DEBIAN_FRONTEND=noninteractive

# Aktualizacja pakietów i instalacja zależności systemowych
RUN apt update && apt install -y \
    software-properties-common \
    && add-apt-repository ppa:deadsnakes/ppa -y \
    && apt update && apt install -y \
    python3.10 \
    python3.10-venv \
    python3.10-dev \
    python3-pip \
    && apt clean

# Ustawienie aliasu dla polecenia `python`
RUN ln -s /usr/bin/python3.10 /usr/bin/python

# Sprawdzenie wersji Pythona
RUN python --version

# Ustawienie katalogu roboczego
WORKDIR /app

# Ustawienie domyślnego polecenia
CMD [ "python" ]
