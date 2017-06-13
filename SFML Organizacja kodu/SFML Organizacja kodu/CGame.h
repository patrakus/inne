#pragma once
#include <SFML/Graphics.hpp>
#include "CLevel.h"

enum Status
{
	Initializing,
	Running,
	Paused,
	CleaningUp
};

class CGame final
{
public:
	
	~CGame();
	CGame(const CGame&) = delete;
	void operator=(const CGame&) = delete; // sprawdziæ czy to jest poprawne

	inline static CGame& Instance()
	{
		static CGame game;
		return game;
	}

	inline Status GetStatus() const
	{
		return m_status;
	}

	inline CLevel* GetCurrentLevel() { return m_currentLevel; }

	void Run();

private:
	CGame();

	sf::RenderWindow m_window;
	Status m_status;
	CLevel* m_currentLevel;
};