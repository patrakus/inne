#pragma once
#include <SFML\System\Vector2.hpp>

class IActor
{
public:
	IActor() : m_rotation(0), m_location()
	{}

	inline virtual ~IActor() {}

	inline virtual void SetLocation(const sf::Vector2<float> &location) { m_location = location; }
	inline virtual void SetRotation(const float & rotation) { m_rotation = rotation; }
	inline void Move(const sf::Vector2<float>& delta) { this->SetLocation(m_location + delta); }
	inline void Rotate(const float &delta) { this->SetRotation(m_rotation + delta); }
	inline virtual void Update(const float& deltaTime) {}

	virtual void Draw() = 0;

	inline sf::Vector2<float> GetLocation() const { return m_location; }
	inline float GetRotation() const { return m_rotation; }

private:
	sf::Vector2<float> m_location;
	float m_rotation;
};