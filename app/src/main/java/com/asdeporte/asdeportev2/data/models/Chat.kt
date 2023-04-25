package com.asdeporte.asdeportev2.data.models

import java.util.*
import kotlin.collections.ArrayList

data class Message (
      var message: String? = null,
      var sender: UserChat? = null,
      var createdAt: Boolean = false,
      var hasImage: HasImage = HasImage.FALSE,
      var timestamp: Date = Date(),
)

class MessageModel {
      companion object {
            fun getGroupMessages(): ArrayList<Message> = arrayListOf(
                  Message("Tempor ex commodo culpa irure amet sunt tempor cupidatat ad anim pariatur quis et sit voluptate.", UserChatModel.getOther(), true, HasImage.TRUE, Date()),
            )
            fun getMessages(): ArrayList<Message> = arrayListOf(
                  Message("Tempor ex commodo culpa irure amet sunt tempor cupidatat ad anim pariatur quis et sit voluptate.", UserChatModel.getOther(), true, HasImage.TRUE, Date()),
                  Message("Cupidatat ad id laborum id non.", UserChatModel.getMe(), false, HasImage.FALSE, Date()),
                  Message("Tempor ex commodo culpa irure amet sunt tempor cupidatat ad anim pariatur quis et sit voluptate.", UserChatModel.getOther(), false, HasImage.TRUE, Date()),
                  Message("Cupidatat ad id laborum id non.", UserChatModel.getMe(), false, HasImage.FALSE, Date()),
                  Message("Tempor ex commodo culpa irure amet sunt tempor cupidatat ad anim pariatur quis et sit voluptate.", UserChatModel.getOther(), false, HasImage.TRUE, Date()),
                  Message("Cupidatat ad id laborum id non.", UserChatModel.getMe(), false, HasImage.FALSE, Date()),
                  Message("Tempor ex commodo culpa irure amet sunt tempor cupidatat ad anim pariatur quis et sit voluptate.", UserChatModel.getOther(), false, HasImage.TRUE, Date()),
                  Message("Cupidatat ad id laborum id non.", UserChatModel.getMe(), false, HasImage.FALSE, Date()),
                  Message("Tempor ex commodo culpa irure amet sunt tempor cupidatat ad anim pariatur quis et sit voluptate.", UserChatModel.getOther(), false, HasImage.TRUE, Date()),
                  Message("Cupidatat ad id laborum id non.", UserChatModel.getMe(), false, HasImage.FALSE, Date()),
                  Message("Tempor ex commodo culpa irure amet sunt tempor cupidatat ad anim pariatur quis et sit voluptate.", UserChatModel.getOther(), false, HasImage.TRUE, Date()),
                  Message("Cupidatat ad id laborum id non.", UserChatModel.getMe(), false, HasImage.FALSE, Date()),
            )
            fun getNew(): Message = Message(
                  "Tempor velit commodo anim.",
                  UserChatModel.getMe(),
                  false,
                  HasImage.FALSE,
                  Date()
            )
      }
}


data class UserChat(
      val nickname: String,
      val profileUrl: String
)

class UserChatModel {
      companion object {
            fun getOther(): UserChat = UserChat(
                  "Mt. Kenya",
                  "https://pbs.twimg.com/media/D8dDZukXUAAXLdY?format=jpg&name=900x900"
            )
            fun getMe(): UserChat = UserChat(
                  "Ewan McGregor",
                  "https://www.themoviedb.org/t/p/w300_and_h450_bestv2/aEmyadfRXTmmR7UW7OXsm5a6smS.jpg"
            )
      }
}


enum class HasImage {
      TRUE, FALSE
}