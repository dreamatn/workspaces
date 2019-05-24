package com.hisun.BP;

import com.hisun.SC.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class BPZQURCY {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String K_CALL_NAME = "BP-RESOURCE-CCY";
    short WS_CCY_CD = 0;
    short WS_CNT1 = 0;
    BPZQURCY_WS_CCY_DATA WS_CCY_DATA = new BPZQURCY_WS_CCY_DATA();
    char WS_TBL_CCY_FLAG = ' ';
    char WS_ORDER = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    BPCRCCY BPCRCCY = new BPCRCCY();
    SCCGWA SCCGWA;
    BPCBCCY BPCBCCY;
    public void MP(SCCGWA SCCGWA, BPCBCCY BPCBCCY) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCBCCY = BPCBCCY;
        CEP.TRC(SCCGWA);
        A00_INIT_PROC();
        if (pgmRtn) return;
        B00_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZQURCY return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A00_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCRCCY);
    }
    public void B00_MAIN_PROC() throws IOException,SQLException,Exception {
        B002_READY_TS_HEAD_TIT();
        if (pgmRtn) return;
        B009_OUTPUT_PTR_REC();
        if (pgmRtn) return;
    }
    public void B002_READY_TS_HEAD_TIT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.TITL = " ";
        SCCMPAG.SUBT_ROW_CNT = 0;
        SCCMPAG.MAX_COL_NO = 208;
        SCCMPAG.SCR_ROW_CNT = 0;
        SCCMPAG.SCR_COL_CNT = 0;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void B009_OUTPUT_PTR_REC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCRCCY);
        BPCRCCY.DATA.CCY_CD = BPCBCCY.CCY_CD;
        WS_CCY_CD = BPCBCCY.CCY_CD;
        BPCRCCY.DATA.CCY = BPCBCCY.CCY;
        CEP.TRC(SCCGWA, BPCRCCY.DATA.CCY_CD);
        CEP.TRC(SCCGWA, BPCRCCY.DATA.CCY);
        if (BPCRCCY.DATA.CCY_CD != 0 
            && BPCRCCY.DATA.CCY.trim().length() == 0) {
            WS_ORDER = 'Y';
        } else {
            WS_ORDER = 'N';
        }
        BPCRCCY.OP_FUNC = 'S';
        S000_CALL_BPZRCCY();
        if (pgmRtn) return;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCRCCY.RC);
        if ((JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_CCY_REC_NOTFND))) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCRCCY.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCBCCY.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        BPCRCCY.OP_FUNC = 'R';
        S000_CALL_BPZRCCY();
        if (pgmRtn) return;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCRCCY.RC);
        while (!(JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_CCY_REC_NOTFND)) 
            && SCCMPAG.FUNC != 'E') {
            R00_OUT_RECORD();
            if (pgmRtn) return;
            if (WS_ORDER == 'N') {
                WS_CNT1 = BPCRCCY.DATA.CCY_CD;
                CEP.TRC(SCCGWA, WS_CNT1);
                T000_WRITE_TS();
                if (pgmRtn) return;
            }
            S000_CALL_BPZRCCY();
            if (pgmRtn) return;
        }
        BPCRCCY.OP_FUNC = 'E';
        S000_CALL_BPZRCCY();
        if (pgmRtn) return;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCRCCY.RC);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCBCCY.RC);
    }
    public void R00_OUT_RECORD() throws IOException,SQLException,Exception {
        WS_CCY_DATA.CCYO_CCY_CD = BPCRCCY.DATA.CCY_CD;
        WS_CCY_DATA.CCYO_CCY = BPCRCCY.DATA.CCY;
        WS_CCY_DATA.CCYO_NAME = BPCRCCY.DATA.CUR_NM;
        WS_CCY_DATA.CCYO_EFF_DT = BPCRCCY.DATA.EFF_DT;
        WS_CCY_DATA.CCYO_EXP_DT = BPCRCCY.DATA.EXP_DT;
        WS_CCY_DATA.CCYO_CNTY = BPCRCCY.DATA.CNTY_CD;
        WS_CCY_DATA.CCYO_CITY = BPCRCCY.DATA.CITY_CD;
        WS_CCY_DATA.CCYO_UNIT_NAME = BPCRCCY.DATA.UNIT_CURU_NAME;
        WS_CCY_DATA.CCYO_CENT_NAME = BPCRCCY.DATA.CENT_CURU_NAME;
        WS_CCY_DATA.CCYO_RGN_FLG = BPCRCCY.DATA.RGN_CCY;
        WS_CCY_DATA.CCYO_DEC_PNT = BPCRCCY.DATA.DEC_MTH;
        WS_CCY_DATA.CCYO_CASH_PNT = BPCRCCY.DATA.CASH_MTH;
        WS_CCY_DATA.CCYO_RND_FLG = BPCRCCY.DATA.RND_MTH;
        WS_CCY_DATA.CCYO_TR_FLG = BPCRCCY.DATA.TR_FLG;
        WS_CCY_DATA.CCYO_CASH_FLG = BPCRCCY.DATA.CASH_FLG;
        WS_CCY_DATA.CCYO_CHG_UNIT = BPCRCCY.DATA.CHGU_MTH;
        WS_CCY_DATA.CCYO_EXH_FLG = BPCRCCY.DATA.EXH_FLG;
        WS_CCY_DATA.CCYO_INT_BASE = BPCRCCY.DATA.CALR_STD;
        WS_CCY_DATA.CCYO_CAN_CD = BPCRCCY.DATA.CAL_CD;
        WS_CCY_DATA.CCYO_UPT_DT = BPCRCCY.DATA.UPT_DT;
        WS_CCY_DATA.CCYO_UPT_TLR = BPCRCCY.DATA.UPT_TLR;
        WS_CCY_DATA.CCYO_ISR_DAYS = BPCRCCY.DATA.ISR_DAYS;
        WS_CCY_DATA.CCYO_BAL_DAYS = BPCRCCY.DATA.BAL_DAYS;
        WS_CCY_DATA.CCYO_CHG_CCY = BPCRCCY.DATA.CHG_CCY;
        WS_CCY_DATA.CCYO_CH_NAME = BPCRCCY.DATA.CUR_CNM;
        CEP.TRC(SCCGWA, BPCRCCY.DATA.CHG_CCY);
        CEP.TRC(SCCGWA, BPCRCCY.DATA.CUR_CNM);
    }
    public void T000_WRITE_TS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, WS_CCY_DATA);
        SCCMPAG.DATA_LEN = 208;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void S000_CALL_BPZRCCY() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, K_CALL_NAME, BPCRCCY);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCRCCY.RC);
        if ((BPCRCCY.RC.RTNCODE != 0) 
            && (!JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_CCY_REC_NOTFND))) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCRCCY.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCBCCY.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B_MPAG() throws IOException,SQLException,Exception {
    if (!SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF ONL
        JIBS_tmp_str[9] = "SCZMPAG";
        Class<?>clazz = Class.forName(JIBS_tmp_str[9].trim());
        Object obj = clazz.newInstance();
        Method m = clazz.getDeclaredMethod("MP",new Class[]{SCCGWA.getClass(), SCCMPAG.getClass()});
        m.invoke(obj, SCCGWA, SCCMPAG);
        if (SCCGWA.COMM_AREA.EXCP_FLG == 'Y') {
            Z_RET();
            if (pgmRtn) return;
        }
    } else { //FROM #ELSE
    } //FROM #ENDIF
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCBCCY.RTNCODE != 0) {
            CEP.TRC(SCCGWA, " BPCBCCY = ");
            CEP.TRC(SCCGWA, BPCBCCY);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
