function splitPostcode(postcode) {
    if (postcode == null) { return null; }
    var fullPostcode = postcode.toUpperCase().trim();

    var inwardCode = '',
        outwardCode = '',
        isInward = false,
        characters = fullPostcode.split('');

    for (var index = 0; index < characters.length; index++) {
        var character = characters[index],
            nextCharacter = ' ';

        switch (index) {
            case 0:
                if (helpers.isLetter(character)) {
                    outwardCode += character;
                }
                break;
            case 1:
                if (helpers.isLetter(character)) {
                    outwardCode += character;
                }
                else if (helpers.isNumber(character)) {
                    outwardCode += character;
                    if (characters.length - 1 > index) {
                        nextCharacter = characters[index + 1];
                        if (helpers.isNumber(nextCharacter) && fullPostcode.length - 2 > index) {
                            nextCharacter = characters[index + 2];
                            isInward = helpers.isLetter(nextCharacter);
                        }
                    }
                }
                break;
            case 2:
                if (character == ' ') {
                    isInward = true;
                }
                else if (isInward) {
                    inwardCode += character;
                }
                else {
                    if (helpers.isLetter(character)) {
                        outwardCode += character;
                    }
                    else if (helpers.isNumber(character)) {
                        outwardCode += character;
                        if (characters.length - 1 > index) {
                            nextCharacter = characters[index + 1];
                            if (helpers.isNumber(nextCharacter) && characters.length - 2 > index) {
                                nextCharacter = characters[index + 2];
                                isInward = helpers.isLetter(nextCharacter);
                            }
                        }
                    }
                }
                break;
            case 3:
                if (isInward) {
                    if (helpers.isNumberOrLetter(character)) {
                        inwardCode += character;
                    }
                }
                else {
                    if (character == ' ') {
                        isInward = true;
                    }
                    else if (helpers.isLetter(character)) {
                        outwardCode += character;
                    }
                    else if (helpers.isNumber(character)) {
                        if (characters.length - 1 > index) {
                            nextCharacter = characters[index + 1];
                            if (helpers.isLetter(nextCharacter)) {
                                isInward = true;
                                inwardCode += character;
                            }
                            else {
                                outwardCode += character;
                            }
                        }
                        else {
                            outwardCode += character;
                        }
                    }
                }
                break;
            case 4:
                if (character == ' ') {
                    isInward = true;
                }
                else if (helpers.isNumberOrLetter(character)) {
                    inwardCode += character;
                }
                break;
            case 5:
                if (helpers.isNumberOrLetter(character)) {
                    inwardCode += character;
                }
                break;
            case 6:
                if (helpers.isNumberOrLetter(character)) {
                    inwardCode += character;
                }
                break;
            case 7:
                if (helpers.isNumberOrLetter(character)) {
                    inwardCode += character;
                }
                break;
        }
    }

    return { 'outwardCode': outwardCode, 'inwardCode': inwardCode };
}