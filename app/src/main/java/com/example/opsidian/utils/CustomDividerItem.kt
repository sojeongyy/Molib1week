import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class CustomDividerItem(
    private val dividerHeight: Int,    // 구분선 높이
    private val dividerColor: Int,     // 구분선 색상
    private val paddingAbove: Int,     // 구분선 위 여백
    private val paddingBelow: Int,     // 구분선 아래 여백
    private val paddingColor: Int      // 여백 색상
) : RecyclerView.ItemDecoration() {

    private val dividerPaint = Paint().apply {
        color = dividerColor
        style = Paint.Style.FILL
    }

    private val paddingPaint = Paint().apply {
        color = paddingColor
        style = Paint.Style.FILL
    }

    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        val left = parent.paddingLeft
        val right = parent.width - parent.paddingRight

        for (i in 0 until parent.childCount - 1) {
            val child = parent.getChildAt(i)
            val params = child.layoutParams as RecyclerView.LayoutParams

            // 여백 위 영역
            val topPaddingTop = child.bottom + params.bottomMargin
            val topPaddingBottom = topPaddingTop + paddingAbove

            // 구분선 영역
            val dividerTop = topPaddingBottom
            val dividerBottom = dividerTop + dividerHeight

            // 여백 아래 영역
            val bottomPaddingTop = dividerBottom
            val bottomPaddingBottom = bottomPaddingTop + paddingBelow

            // 위 여백 색상 그리기
            c.drawRect(left.toFloat(), topPaddingTop.toFloat(), right.toFloat(), topPaddingBottom.toFloat(), paddingPaint)

            // 구분선 색상 그리기
            c.drawRect(left.toFloat(), dividerTop.toFloat(), right.toFloat(), dividerBottom.toFloat(), dividerPaint)

            // 아래 여백 색상 그리기
            c.drawRect(left.toFloat(), bottomPaddingTop.toFloat(), right.toFloat(), bottomPaddingBottom.toFloat(), paddingPaint)
        }
    }

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        // 아이템 아래쪽 여백과 구분선의 높이 포함
        outRect.bottom = paddingAbove + dividerHeight + paddingBelow
    }
}
