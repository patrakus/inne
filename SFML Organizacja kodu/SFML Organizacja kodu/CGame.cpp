#include "CGame.h"



CGame::CGame(): m_window(sf::VideoMode(800, 600), "SFML works!"),
				m_status(Status::Initializing),
				m_currentLevel()
{
}


CGame::~CGame()
{
	if (m_window.isOpen())
		m_window.close();
	if (m_currentLevel)
		delete m_currentLevel;
}

void CGame::Run()
{
	m_status = Status::Running;

	sf::Color bgColor(0, 0, 0);

	sf::Clock GameClock;

	float DeltaTime = 1.f / 60.f;

	while (m_status != Status::CleaningUp)
	{
		float frameStartTime = GameClock.getElapsedTime().asSeconds();

		sf::Event windowEvent;
		while (m_window.pollEvent(windowEvent))
		{
			if (windowEvent.type == sf::Event::Closed)
				m_status = Status::CleaningUp;
		}

		m_window.clear(bgColor);

		m_currentLevel->Update(DeltaTime);
		m_currentLevel->Draw();

		m_window.display();

		DeltaTime = GameClock.getElapsedTime().asSeconds() - frameStartTime;
	}
}