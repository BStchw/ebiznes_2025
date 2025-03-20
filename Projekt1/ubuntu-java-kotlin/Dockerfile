# Użyj Ubuntu 24.04 jako bazowego obrazu
FROM ubuntu:24.04

# Ustaw zmienną środowiskową, aby uniknąć interaktywnych pytań podczas instalacji
ENV DEBIAN_FRONTEND=noninteractive

# Aktualizacja pakietów i instalacja Java 8 (OpenJDK)
RUN apt update && apt install -y \
    openjdk-8-jdk \
    curl \
    unzip \
    && apt clean

# Ustawienie JAVA_HOME dla Java 8
ENV JAVA_HOME=/usr/lib/jvm/java-8-openjdk-amd64
ENV PATH="${JAVA_HOME}/bin:${PATH}"

# Pobranie i instalacja Kotlina
RUN curl -sSLO https://github.com/JetBrains/kotlin/releases/download/v1.9.0/kotlin-compiler-1.9.0.zip \
    && unzip kotlin-compiler-1.9.0.zip -d /opt/kotlin \
    && rm kotlin-compiler-1.9.0.zip

# Ustawienie ścieżki dla Kotlina
ENV PATH="/opt/kotlin/kotlinc/bin:${PATH}"

# Sprawdzenie wersji Java i Kotlin
RUN java -version && kotlinc -version

# Ustawienie katalogu roboczego
WORKDIR /app

# Ustawienie domyślnego polecenia
CMD [ "bash" ]
