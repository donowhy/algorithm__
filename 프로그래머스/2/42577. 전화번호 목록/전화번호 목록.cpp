#include <algorithm>
#include <string>
#include <vector>

using namespace std;

bool solution(vector<string> phone_book) {
    sort(phone_book.begin(), phone_book.end());

    for (int i = 0; i + 1 < phone_book.size(); ++i) {
        const string& current = phone_book[i];
        const string& next = phone_book[i + 1];

        if (next.compare(0, current.size(), current) == 0) {
            return false;
        }
    }

    return true;
}