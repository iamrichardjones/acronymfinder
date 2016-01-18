# Mnemonic Revision Helper

Exams are the bane of the lives of students. When I was memorising things I often used mnemonics to remember. For example

*Richard Of York Gave Battle In Vain*

This is an easy way to remember the colours of the rainbow which are Red, Orange, Yellow, Green, Blue, Indigo and Violet.

It's a fact in life that advertising by brands is a hugely effective way of making people remember slogans.
 
 These can be broken down into mnemonics too. Below are some example of very popular slogans

|Slogan|Company|
|-------|-------|
|The ultimate driving machine |BMW|
|The world's favourite airline.  |British Airways|
|It's good to talk               |British Telecom|
|Just Do It      |Nike|

For example if I wanted to remember a list of things that was _DIJ_ and the order made no meaning then I could remember Nike's slogan
By remembering Nike's slogan I know that the the three items in this list each start with a J, a D and an I

This does not just relate to slogans too. Song titles could be used to remember among other things. 

This project allows you to enters letters that you want to memorise and then it returns (hopefully) a more memorable mnemonic to help jog your memory.

And I could not do a project on mnemonics without referencing [xkcd](https://xkcd.com/992/)

### How to build
1. Clone repository
2. Run gradle clean build
 
### How to run
Several ways

#### Method1
1. Clone repository
2. Run gradle clean build
3. Find /build/libs
4. Run java -jar MnemonicRevisionHelper-1.0.jar

#### Method2
1. Clone repository
2. Run gradle clean build
3. Run core.Runner with parameter -D for debug. (-D will help with loading resources)

#### Method3
1. Clone repository
2. Run gradle clean build
3. Run gradle installDist
3. installDist will create the distribution
4. Find /build/install/MnemonicRevisionHelper/bin
5. Run the bat or shell file (eg MnemonicRevisionHelper.bat)

### Translations:
Many thanks to: 
* Sabine Reisinger for the German translation
* [Martin Spasov](https://github.com/MartinYSpasov) for the Bulgarian translation
* Debbie Cooke for the Spanish translation

### TODO:
* finish I18N and test
* Add more Mnemonics  (on-going) - Currently over 500 Slogans and 5200 Songs

### Legal Bit
All data used in this project is sourced from the internet. I take no responsibility for any errors. Please let me know if there are problems or if any bits should be removed.
