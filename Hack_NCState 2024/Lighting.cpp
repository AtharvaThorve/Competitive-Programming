#include <bits/stdc++.h>

using namespace std;

int main()
{
    auto dist = [](int x1, int y1, int x2, int y2)
    {
        return sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
    };

    int t;
    cin >> t;
    while (t--)
    {
        int px, py, ax, ay, bx, by;
        cin >> px >> py >> ax >> ay >> bx >> by;
        double pa = dist(px, py, ax, ay), pb = dist(px, py, bx, by);
        double oa = dist(0, 0, ax, ay), ob = dist(0, 0, bx, by);
        double ab = dist(ax, ay, bx, by);
        double ans = 1e9;
        ans = min(ans, max(pa, oa));
        ans = min(ans, max(pb, ob));
        ans = min(ans, max({ab / 2, pa, ob}));
        ans = min(ans, max({ab / 2, pb, oa}));
        cout << setprecision(10) << fixed << ans << '\n';
    }
}
