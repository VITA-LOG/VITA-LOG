<img src="./images/1.png" width="100%" height="100%"/>

--------------------------------------------------------------------------------

VITA LOG is an abbreviation of Vitamin + V-log, which is an app service for managing supplement information and related health history.
Nowadays, awareness and issues of the health and wellness are growing, one of the factors of health care is taking nutritional supplement.
We are going to proceed with a project of nutritional coaching application project through various nutritional contents.

VITA LOG provides five high-level features:
- Nutrition information
- Customized nutritional coaching based on user health condition
- Latest news/paper contents
- Database search and OCR image search
- GPT-4 AI Chatbot

<!-- toc -->

- [Development](#development)
  - [Prototype](#prototype)
  - [Demonstration](#demonstration)
  - [IDE](#ide)
  - [YouTube](#youtube)
- [Design](#design)
  - [UX Principle](#ux-principle)
  - [Service Map](#service-map)
  - [BluePrint](#blueprint)
- [Getting Started](#getting-started)
  - [GPT-4 API Key](#gpt-4-api-key)
- [The Team](#the-team)
- [License](#license)

<!-- tocstop -->

## Development

### Prototype

<img src="./images/1-1.png" width="100%" height="100%"/>

### Demonstration

<p align="left">
  <img src="./images/9-1.jpg" width="24%" height="20%"/>
  <img src="./images/9-2.jpg" width="24%" height="20%"/>
  <img src="./images/9-8.jpg" width="24%" height="20%"/>
  <img src="./images/9-9.jpg" width="24%" height="20%"/>
  <img src="./images/10-1.jpg" width="24%" height="20%"/>
  <img src="./images/10-2.jpg" width="24%" height="20%"/>
  <img src="./images/9-3.jpg" width="24%" height="20%"/>
  <img src="./images/9-4.jpg" width="24%" height="20%"/>
  <img src="./images/9-5.jpg" width="24%" height="20%"/>
  <img src="./images/9-6.jpg" width="24%" height="20%"/>
  <img src="./images/9-7.jpg" width="24%" height="20%"/>
  <img src="./images/10-3.jpg" width="24%" height="20%"/>
</p>

### IDE

* <img src="https://img.shields.io/badge/Kotlin-7F52FF?style=flat&logo=Kotlin&logoColor=white"/>
* <img src="https://img.shields.io/badge/Android Studio-3DDC84?style=flat&logo=Android Studio&logoColor=white"/>
* <img src="https://img.shields.io/badge/Firebase-FFCA28?style=flat&logo=Firebase&logoColor=white"/>
* <img src="https://img.shields.io/badge/Figma-F24E1E?style=flat&logo=Figma&logoColor=white"/>

### YouTube

[![Video Label](http://img.youtube.com/vi/spHKSBqX7LQ/0.jpg)](https://youtu.be/spHKSBqX7LQ?feature=shared)

## Design

### UX Principle

<img src="./images/3.png" width="100%" height="100%"/>

### Service Map

<img src="./images/2-2.png" width="60%" height="50%"/>

### BluePrint

<img src="./images/2-1.png" width="100%" height="100%"/>

## Getting Started

### GPT-4 API Key

Get an API key from the [OpenAI website](https://platform.openai.com).

Put the issued key in the following openAIApiKey line.

```
package com.daineey.vita_log.constants

const val openAIApiKey = ""

const val urlToImageAppIcon = "https://res.cloudinary.com/apideck/image/upload/v1672442492/marketplaces/ckhg56iu1mkpc0b66vj7fsj3o/listings/-4-ans_frontend_assets.images.poe.app_icon.png-26-8aa0a2e5f237894d_tbragv.png"
const val urlToImageAuthor = "https://avatars.githubusercontent.com/u/33478798?v=4"
const val urlToAvatarGPT = "app/src/main/res/drawable/ic_vitalog_app.png"
const val urlToGithub = "https://github.com/YoungJensKR"

const val conversationTestTag = "ConversationTestTag"
```

## The Team

VITA LOG is a Hongik University graduation project with two developers contributing to it.

VITA LOG is currently maintained by [Yeong-Jin Kim](https://github.com/youngjeanskr), [Da-In Hong](https://github.com/daineey)

## License

VITA LOG has a Apache License 2.0, as found in the [LICENSE](LICENSE) file.
