import pandas
import random
from tkinter import *

BACKGROUND_COLOR = "#B1DDC6"
turn_card = None
first_time = False

# List of words from the file
try:
    data_frame = pandas.read_csv("./data/words_to_learn.csv")
except FileNotFoundError:
    data_frame = pandas.read_csv("./data/french_words.csv")
    first_time = True

list_words = pandas.DataFrame.to_dict(data_frame, orient="records")
word_to_show = random.choice(list_words)


# UI of application
def front_of_card():
    global word_to_show, turn_card

    word_to_show = random.choice(list_words)
    canvas.itemconfig(card_image, image=card_front)
    canvas.itemconfig(language_text, text="French")
    canvas.itemconfig(word_text, text=word_to_show["French"])
    turn_card = window.after(3000, back_of_card)


def back_of_card():
    global turn_card

    window.after_cancel(turn_card)
    canvas.itemconfig(card_image, image=card_back)
    canvas.itemconfig(language_text, text="English")
    canvas.itemconfig(word_text, text=word_to_show["English"])


def correct_word():
    list_words.remove(word_to_show)
    front_of_card()


def save_on_close():
    new_df = pandas.DataFrame(list_words)
    new_df.to_csv("./data/words_to_learn.csv", index=False)
    window.destroy()


window = Tk()
window.config(padx=50, pady=50, bg=BACKGROUND_COLOR)

card_front = PhotoImage(file="./images/card_front.png")
card_back = PhotoImage(file="./images/card_back.png")
right_image = PhotoImage(file="./images/right.png")
wrong_image = PhotoImage(file="./images/wrong.png")

canvas = Canvas(width=800, height=528, highlightthickness=0, bg=BACKGROUND_COLOR)
card_image = canvas.create_image(400, 264, image=card_front)
language_text = canvas.create_text(400, 150, text="French", font=("Arial", 40, "italic"))
word_text = canvas.create_text(400, 263, text=word_to_show["French"], font=("Arial", 60, "bold"))
canvas.grid(row=0, column=0, columnspan=2)

right_button = Button(image=right_image, highlightthickness=0, command=correct_word)
right_button.grid(row=1, column=0)

wrong_button = Button(image=wrong_image, highlightthickness=0, command=front_of_card)
wrong_button.grid(row=1, column=1)

front_of_card()

window.protocol("WM_DELETE_WINDOW", save_on_close)

window.mainloop()
