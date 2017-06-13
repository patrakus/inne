#include "TextureManager.h"



CTextureManager::CTextureManager()
{
}


CTextureManager::~CTextureManager()
{
	for (auto textureData : m_textures)
	{
		delete(textureData.second);
	}
}

sf::Texture* CTextureManager::Load(const std::string & textureName, const std::string & texturePath)
{
	sf::Texture* result = CTextureManager::Get(textureName);

	if (result)
	{
		result->loadFromFile(texturePath);
	}
	else
	{
		result = new sf::Texture();
		result->loadFromFile(texturePath);
		Instance().m_textures[textureName] = result;
	}

	return result;
}

bool CTextureManager::Unload(const std::string & textureName)
{
	auto &instance = Instance();

	auto textureIt = instance.m_textures.find(textureName);

	if (textureIt == instance.m_textures.end())
	{
		return false;
	}
	else
	{
		delete (textureIt->second);
		instance.m_textures.erase(textureIt);

		return true;
	}
}

std::size_t CTextureManager::Cleanup()
{
	auto &instance = Instance();

	std::size_t textureCount = instance.m_textures.size();

	for (auto textureData : instance.m_textures)
	{
		delete (textureData.second);
	}

	return textureCount;
}

sf::Texture * CTextureManager::Get(const std::string & textureName)
{
	auto &instance = Instance();
	auto textureIt = instance.m_textures.find(textureName);

	if (textureIt == instance.m_textures.end())
	{
		return nullptr;
	}

	return textureIt->second;
}
