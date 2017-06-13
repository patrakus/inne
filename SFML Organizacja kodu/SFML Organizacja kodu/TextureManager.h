#pragma once
#include <unordered_map>
#include <SFML\Graphics.hpp>

class CTextureManager final
{
public:

	typedef std::unordered_map<std::string, sf::Texture* > TTexturesUM;

	~CTextureManager();

	CTextureManager(const CTextureManager&) = delete;
	void operator=(const CTextureManager&) = delete;

	static sf::Texture* Load(const std::string &textureName, const std::string & texturePath);
	static bool Unload(const std::string &textureName);
	static std::size_t Cleanup();
	static sf::Texture* Get(const std::string &textureName);

	inline static bool Exists(const std::string &textureName) { return CTextureManager::Get(textureName) != nullptr; }

private:
	CTextureManager();

	inline static CTextureManager& Instance()
	{
		static CTextureManager instance;
		return instance;
	}

	TTexturesUM m_textures;
};

