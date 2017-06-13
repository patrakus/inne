#include "Game.h"

int main()
{
	auto &game = CGame::Instance();

	game.Run();

	return 0;
}