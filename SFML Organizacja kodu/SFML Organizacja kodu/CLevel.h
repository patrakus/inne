#pragma once
#include <vector>
#include "IActor.h"

class CLevel final
{
public:

	typedef std::vector<IActor* > TActorsV;

	CLevel();
	~CLevel();

	bool Add(IActor* actor);
	bool Remove(IActor* actor);
	bool Exists(IActor* actor);
	std::size_t Cleanup();
	void Update(const float &deltaTime);
	void Draw();

	inline std::size_t Count() const { return m_actors.size(); }
	
private:
	TActorsV m_actors;
};

