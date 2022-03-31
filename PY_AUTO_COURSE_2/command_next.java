//Включаем режим отладки
debug.enable();

//Фиксируем скришоты: у студента только 1 день на выполнение задания в срок. Фиксируем семизначном числе-переменной

def tgUserInfo = telegramUser.userId + ' (' + telegramUser.getFullName() + ')';

//Смотрим, завершил ли пользователь игру. До этого момента никаких скриншотов не принимаем
// def finishedPythonGame = Boolean.parseBoolean(commonActions.getUserVariable(user, 'finishedPythonMarathon', false));
// if (!finishedPythonGame) {
//     return;
// }

// Смотрим текущую дату - оцениваем только тот скриншот, который отправили в день задания

//Смотрим, нужно ли вообще отправлять пользователю сообщение о проверке скриншота. Если отправляли в этом дне - не отправляем еще раз
def sentPythonBotScreenshot = Boolean.parseBoolean(commonActions.getUserVariable(user, 'sentPythonBotScreenshotToday', false));
if (sentPythonBotScreenshot) {
    debug.log(tgUserInfo, 'Уже отправили сообщение, что проверяем его скриншот - еще раз не отправляем');
    
    return;
}

//Устанавливаем переменную
commonActions.setUserVariable(user, 'sentPythonBotScreenshotToday', true);


//Получаем нужное сообщение
def message =
'''
Спасибо! Ваше задание на проверке. 🙂
''';

//Отсылаем сообщение пользователю
commonActions.sendTelegramSimpleTextMessage(message, [telegramUser: telegramUser, sendOrdered: false, botName: 'goiteens_python_bot']);

debug.log(tgUserInfo, '[SENT MESSAGE] Пишем, что проверим скриншот, полный текст сообщения: ' + message);