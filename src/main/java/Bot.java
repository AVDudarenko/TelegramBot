import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendAudio;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class Bot extends TelegramLongPollingBot {

	private String id;

	@Override
	public String getBotUsername() {
		return "practice_music_bot";
	}

	@Override
	public String getBotToken() {
		return "5778345215:AAFqTpT3uBvHJrl1JWM5nOyRiU-b2z4eITE";
	}

	@Override
	public void onUpdateReceived(Update update) {


//		getAudioId(update);
		sendAudioThreeTimes(update, "CQACAgIAAxkBAAMJYxr2Tz08A6Aisy_TxXbjKjkYtX8AAlsiAALx89hIxdmFvSQ2XzgpBA", "My caption");

	}

	private void getAudioId(Update update) {
		SendMessage sendMessage = new SendMessage();
		sendMessage.setChatId(update.getMessage().getChatId());
		id = update.getMessage().getAudio().getFileId();
		sendMessage.setText(id);
		try {
			execute(sendMessage);
		} catch (TelegramApiException e) {
			throw new RuntimeException(e);
		}
	}

	private void sendAudio(Update update, String fileId, String caption) {
		SendAudio sendAudio = new SendAudio();
		sendAudio.setChatId(update.getMessage().getChatId());
		sendAudio.setAudio(new InputFile(fileId));
		sendAudio.setCaption(caption);
		try {
			execute(sendAudio);
		} catch (TelegramApiException e) {
			throw new RuntimeException(e);
		}
	}

	private void sendAudioThreeTimes(Update update, String fileId, String caption) {
		SendAudio sendAudio = new SendAudio();
		sendAudio.setChatId(update.getMessage().getChatId());
		sendAudio.setAudio(new InputFile(fileId));
		sendAudio.setCaption(caption);
		try {
			for (int i = 0; i < 3; i++) {
				execute(sendAudio);
			}
		} catch (TelegramApiException e) {
			throw new RuntimeException(e);
		}
	}

	private void sendMessage(Update update) {
		String name = update.getMessage().getText();
		SendMessage sendMessage = new SendMessage();
		sendMessage.setChatId(update.getMessage().getChatId());
		sendMessage.setText("Hello there, " + name);
		sendMessage.setText(id);
		try {
			execute(sendMessage);
		} catch (TelegramApiException e) {
			throw new RuntimeException(e);
		}
	}
}
