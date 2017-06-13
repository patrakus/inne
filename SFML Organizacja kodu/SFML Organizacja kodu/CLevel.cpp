#include "CLevel.h"



CLevel::CLevel()
{
}


CLevel::~CLevel()
{
	this->Cleanup();
}

bool CLevel::Add(IActor * actor)
{
	if (actor)
	{
		if (!this->Exists(actor))
		{
			m_actors.push_back(actor);
			return true;
		}
	}
	return false;
}

bool CLevel::Remove(IActor * actor)
{
	if (actor)
	{
		auto actorIt = std::find(m_actors.begin(), m_actors.end(), actor);
		if (actorIt != m_actors.end())
		{
			m_actors.erase(actorIt);
			return true;
		}
	}
	return false;
}

bool CLevel::Exists(IActor * actor)
{
	return std::find(m_actors.begin(), m_actors.end(), actor) != m_actors.end(); // TODO: sprawdziæ metody begin end
}

std::size_t CLevel::Cleanup()
{
	std::size_t actorsCount = m_actors.size();
	for (auto *actor : m_actors)
		delete actor;
	return actorsCount;
}

void CLevel::Update(const float & deltaTime)
{
	for (auto *actor : m_actors)
		actor->Update(deltaTime);
}

void CLevel::Draw()
{
	for (auto *actor : m_actors)
		actor->Draw();
}
