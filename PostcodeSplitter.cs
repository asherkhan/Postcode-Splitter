using System;

namespace Helpers
{
    public class Postcode
    {
        public string InwardCode { get; set; }
        public string OutwardCode { get; set; }
    }

    public static class PostcodeSplitter
    {
        public static Postcode Split(string postcode)
        {
            if (String.IsNullOrEmpty(postcode)) { return null; }

            var fullPostcode = postcode.ToUpper().Trim();
            var inwardCode = string.Empty;
            var outwardCode = string.Empty;
            var isInward = false;
            var characters = fullPostcode.ToCharArray();

            for (var i = 0; i < characters.Length; i++)
            {
                var character = characters[i];
                var nextCharacter = ' ';
                switch (i)
                {
                    case 0:
                        if (Char.IsLetter(character))
                        {
                            outwardCode += character;
                        }
                        break;
                    case 1:
                        if (Char.IsLetter(character))
                        {
                            outwardCode += character;
                        }
                        else if (Char.IsNumber(character))
                        {
                            outwardCode += character;
                            if (characters.Length - 1 > i)
                            {
                                nextCharacter = characters[i + 1];
                                if (Char.IsNumber(nextCharacter) && fullPostcode.Length - 2 > i)
                                {
                                    nextCharacter = characters[i + 2];
                                    isInward = Char.IsLetter(nextCharacter);
                                }
                            }
                        }
                        break;
                    case 2:
                        if (character == ' ')
                        {
                            isInward = true;
                        }
                        else if (isInward)
                        {
                            inwardCode += character;
                        }
                        else
                        {
                            if (Char.IsLetter(character))
                            {
                                outwardCode += character;
                            }
                            else if (Char.IsNumber(character))
                            {
                                outwardCode += character;
                                if (characters.Length - 1 > i)
                                {
                                    nextCharacter = characters[i + 1];
                                    if (Char.IsNumber(nextCharacter) && characters.Length - 2 > i)
                                    {
                                        nextCharacter = characters[i + 2];
                                        isInward = Char.IsLetter(nextCharacter);
                                    }
                                }
                            }
                        }
                        break;
                    case 3:
                        if (isInward)
                        {
                            if (Char.IsLetter(character) || Char.IsNumber(character))
                            {
                                inwardCode += character;
                            }
                        }
                        else
                        {
                            if (character == ' ')
                            {
                                isInward = true;
                            }
                            else if (Char.IsLetter(character))
                            {
                                outwardCode += character;
                            }
                            else if (Char.IsNumber(character))
                            {
                                if (characters.Length - 1 > i)
                                {
                                    nextCharacter = characters[i + 1];
                                    if (Char.IsLetter(nextCharacter))
                                    {
                                        isInward = true;
                                        inwardCode += character;
                                    }
                                    else
                                    {
                                        outwardCode += character;
                                    }
                                }
                                else
                                {
                                    outwardCode += character;
                                }
                            }
                        }
                        break;
                    case 4:
                        if (character == ' ')
                        {
                            isInward = true;
                        }
                        else if (Char.IsNumber(character) || Char.IsLetter(character))
                        {
                            inwardCode += character;
                        }
                        break;
                    case 5:
                        if (Char.IsLetter(character) || Char.IsNumber(character))
                        {
                            inwardCode += character;
                        }
                        break;
                    case 6:
                        if (Char.IsLetter(character) || Char.IsNumber(character))
                        {
                            inwardCode += character;
                        }
                        break;
                    case 7:
                        if (Char.IsLetter(character) || Char.IsNumber(character))
                        {
                            inwardCode += character;
                        }
                        break;
                }
            }
            return new Postcode() { InwardCode = inwardCode, OutwardCode = outwardCode };
        }
    }
}