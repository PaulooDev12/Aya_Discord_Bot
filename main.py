from os import getenv
import random
from time import process_time_ns

import discord
from discord.ext import commands
from dotenv import load_dotenv

load_dotenv()

intents = discord.Intents.default()
intents.message_content = True
bot = commands.Bot(command_prefix='$', intents=intents)

vocaloids_images = {
    "Teto": "https://cdn.discordapp.com/attachments/1071496420483399700/1530781874786406400/6f961039ecfe754af7a36f3e1ded5e40.jpg?ex=6a66d337&is=6a6581b7&hm=0c2af02b986cd0625db5c298a41d5c7d412a22ab57e7f0f6cc9677b61c31ff04&",
    "Miku": "https://cdn.discordapp.com/attachments/1071496420483399700/1530781903035170956/f62b36fe7a47fcb7.jpg?ex=6a66d33e&is=6a6581be&hm=3af19bd08560c978b678cd4b7b8120c8c68f2acc2da862e8122dea3f3d8280f3&",
    "Gumi": "https://cdn.discordapp.com/attachments/1071496420483399700/1530781849285300274/icon_gumi_.jpg?ex=6a66d331&is=6a6581b1&hm=cc7173b6dc8f847dfcb4fd28718189ddad021e3dfba58c287adaa0cc30ed0a04&",
    "Neru": "https://cdn.discordapp.com/attachments/1071496420483399700/1530783087758278707/Neru__.jpg?ex=6a66d458&is=6a6582d8&hm=5416583ec486985a1202f9ced6d427f502a97d1e6d774f2b6609e32a13d7fc1b&"
}
waifus_images = {

    "Nino": "https://cdn.discordapp.com/attachments/1071496420483399700/1530785456571289691/nino.jpg?ex=6a66d68d&is=6a65850d&hm=40f9c8c7f30fb74d412cd313f1ce1e924cd842e821921b20a85feaf3da266429&",

    "Itsuki": "https://cdn.discordapp.com/attachments/1071496420483399700/1530785456914956308/Itsuki_Nakano_.jpg?ex=6a66d68d&is=6a65850d&hm=49c40cbe66f73621d7c2a62d059c585142571449195509fd79051e13319edd31&",

    "Ichika": "https://cdn.discordapp.com/attachments/1071496420483399700/1530785457145905273/Ichika_Nakano_.jpg?ex=6a66d68d&is=6a65850d&hm=039c5261e7c040dd3d91256143fa2dad651dfce23f335137793634d33d09e24c&",

    "Miku": "https://cdn.discordapp.com/attachments/1071496420483399700/1530785457376595968/Miku.jpg?ex=6a66d68d&is=6a65850d&hm=7684d111c299378cba40e4eb8b642a009d175093e5b0d84e80ad5caa8f4f258d&",

    "Yotsuba": "https://cdn.discordapp.com/attachments/1071496420483399700/1530785457644896336/download_15.jpg?ex=6a66d68d&is=6a65850d&hm=026750a8314773e57cdaf3dd5e45918dd74e123db7fb0a8a4156259c8924df75&"
}
colecoes = {}
@bot.event
async def on_ready():
    try:
        synced = await bot.tree.sync()
        print("Synced")
    except Exception as e:
        print(f"Erro ao sincronizar comandos: {e}")
    print(f'Logged in as {bot.user}')
@bot.tree.command()
async def ping(interaction: discord.Interaction):
    await interaction.response.send_message('pong')

@bot.tree.command(name="hi", description="Te diz oi")
async def ola(interaction: discord.Interaction):
    name = interaction.user.display_name
    await interaction.response.send_message(f'ola {name} como esta')

@bot.tree.command(name="random_quintuplete", description="Te da uma quintupla aleatoria")
async def jogo(interaction: discord.Interaction):
    id = interaction.user.id

    key = random.choice(list(waifus_images.keys()))
    if not id in colecoes:
        colecoes[id] = set(
        )
    colecao = colecoes[id]
    image = waifus_images[key]
    embed = discord.Embed(title=key)
    embed.set_image(url=image)
    colecao.add(key)
    if len(colecao) == 5:
        name = interaction.user.display_name
        embed1 = discord.Embed(title="Parabens!!!", description=f"{name}, Voce Venceu o Gacha das Quintuplas")
        embed1.set_image(url="https://cdn.discordapp.com/attachments/1071496420483399700/1530789131830034572/download_16.jpg?ex=6a66d9f9&is=6a658879&hm=de544b12bc9eaaaa853babeb9b44500734f19921aaae1b57b90b11325b87aea5&")
        await interaction.response.send_message(embed=embed1)
        colecao.clear()
        return
    await interaction.response.send_message(embed=embed)
@bot.tree.command(name="chamar_user", description="Chama um usuario e marca no chat com sua foto")
async def chamar_user(interaction: discord.Interaction, user: discord.Member):
    avatar_url = user.display_avatar.url
    await interaction.response.send_message(f"ola {user.mention} {avatar_url}")
@bot.tree.command(name="vocaloid", description="Vocaloid aleatoria")
async def teto(interaction: discord.Interaction):
    key = random.choice(list(vocaloids_images.keys()))
    url = vocaloids_images[key]
    embed = discord.Embed(title=key)
    embed.set_image(url=url)
    await interaction.response.send_message(embed=embed)
token = getenv('TOKEN')
bot.run(token)
