package com.hisun.DC;

import com.hisun.SC.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class DCZSUUCQ {
    brParm DCTCTCDC_BR = new brParm();
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String K_OUTPUT_FMT = "DC407";
    short K_MAX_ROWS = 20;
    String WS_ERR_MSG = " ";
    char WS_CNT_FLG = ' ';
    char WS_TBL_FLAG = ' ';
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCMSG SCCMSG = new SCCMSG();
    DCRCITCD DCRCITCD = new DCRCITCD();
    DCRCTCDC DCRCTCDC = new DCRCTCDC();
    DCCF407 DCCF407 = new DCCF407();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    DCCSUUCQ DCCSUUCQ;
    public void MP(SCCGWA SCCGWA, DCCSUUCQ DCCSUUCQ) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DCCSUUCQ = DCCSUUCQ;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DCZSUUCQ return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        B020_GET_CARD_INFO();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        if (DCCSUUCQ.APP_BR == 0 
            || DCCSUUCQ.CHG_STS == ' ') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_MUST_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B020_GET_CARD_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCRCTCDC);
        DCRCTCDC.APP_BR = DCCSUUCQ.APP_BR;
        DCRCTCDC.CHG_STS = DCCSUUCQ.CHG_STS;
        T000_STARTBR_DCTCTCDC();
        if (pgmRtn) return;
        T000_READNEXT_DCTCTCDC();
        if (pgmRtn) return;
        C010_OUT_INI();
        if (pgmRtn) return;
        while (WS_TBL_FLAG != 'N' 
            && SCCMPAG.FUNC != 'E') {
            C020_OUT_LIST();
            if (pgmRtn) return;
            T000_READNEXT_DCTCTCDC();
            if (pgmRtn) return;
        }
        T000_ENDBR_DCTCTCDC();
        if (pgmRtn) return;
    }
    public void C010_OUT_INI() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.TITL = " ";
        SCCMPAG.SUBT_ROW_CNT = 0;
        SCCMPAG.MAX_COL_NO = 143;
        SCCMPAG.SCR_ROW_CNT = K_MAX_ROWS;
        SCCMPAG.SCR_COL_CNT = 0;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void C020_OUT_LIST() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCF407);
        DCCF407.O_OLD_CARD_NO = DCRCTCDC.KEY.OLD_CARD_NO;
        DCCF407.O_CHG_STS = DCRCTCDC.CHG_STS;
        DCCF407.O_ID_TYP = DCRCTCDC.ID_TYP;
        DCCF407.O_ID_NO = DCRCTCDC.ID_NO;
        DCCF407.O_NEW_CARD_NO = DCRCTCDC.NEW_CARD_NO;
        DCCF407.O_TXN_DT = DCRCTCDC.TXN_DT;
        DCCF407.O_APP_BR = DCRCTCDC.APP_BR;
        DCCF407.O_HDOV_DT = DCRCTCDC.HDOV_DT;
        DCCF407.O_HDOV_BR = DCRCTCDC.HDOV_BR;
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, DCCF407);
        SCCMPAG.DATA_LEN = 143;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void T000_STARTBR_DCTCTCDC() throws IOException,SQLException,Exception {
        DCTCTCDC_BR.rp = new DBParm();
        DCTCTCDC_BR.rp.TableName = "DCTCTCDC";
        DCTCTCDC_BR.rp.where = "APP_BR = :DCRCTCDC.APP_BR "
            + "AND CHG_STS = :DCRCTCDC.CHG_STS";
        DCTCTCDC_BR.rp.order = "CHG_SEQ DESC";
        IBS.STARTBR(SCCGWA, DCRCTCDC, this, DCTCTCDC_BR);
    }
    public void T000_READNEXT_DCTCTCDC() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, DCRCTCDC, this, DCTCTCDC_BR);
        WS_TBL_FLAG = ' ';
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TBL_FLAG = 'N';
        } else {
    }
    public void T000_ENDBR_DCTCTCDC() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, DCTCTCDC_BR);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
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
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
