# blast

<div align="center">
  <a href="https://www.oracle.com/java/" target="_blank">
    <img
      src="https://img.shields.io/badge/Written%20in-java-%23EF4041?style=for-the-badge"
      height="30"
    />
  </a>
  <a href="https://spring.io/" target="_blank">
    <img
      src="https://img.shields.io/badge/spring-boot-%27a147?style=for-the-badge"
      height="30"
    />
  </a>
</div>

## 📚 Introduction

`blast` is a simple and fast REST API for sending mails. It communicates with your mailserver via SMTP. As a result, it is required to have a working SMTP server

## 💿 Installation

### 🐳 dockerized

```bash
$ git clone https://github.com/Clientastisch/blast
$ docker-compose up
```

Starts on port `:8082`

### 📦 standalone

Make sure to have `maven` installed.

```bash
$ mvn install -B -ntp -DskipTests=true -f pom.xml
$ java -jar target/blast-1.0.jar
```

Starts on port `:8080`

## 🚧 API

`blast` has only a single endpoint at `/api/v1/mail/send` to send mails.

| Argument (required) | Description |
| --- | --- |
| `host`: string | The hostname of the mail server (mail.myhost.com) |
| `port`: integer | The port of the mail server (default: 25) |
| `starttls`: boolean | Enable tls if your mailserver supports it |
| `auth`: object | json object with `mail` and `password` |
| `message`: object | json object with `recipient`, `subject` and `text` |

<details>
 <summary> JavaScript fetch </summary>

```js
fetch("http://myhost.com:8082/api/v1/mail/send", {
    method: 'POST',
    headers: {
        'Content-Type': 'application/json',
    },
    body: JSON.stringify({
        'host': 'mail.myhost.dev',
        'port': 25,
        'starttls': false,
        'auth': {
            'mail': 'noreply@myhost.dev',
            'password': 'mypassword'
        },
        'message': {
            'recipient': 'someone@gmail.com',
            'subject': 'Foo',
            'text': 'Shit is getting real'
        }
    })
})
```
</details>