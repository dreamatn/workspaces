package com.hisun.DC;

import com.hisun.DD.*;
import com.hisun.BP.*;
import com.hisun.SC.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class DCZIYJUP {
    brParm DCTSSTS_BR = new brParm();
    DBParm DCTSSTS_RD;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String WS_ERR_MSG = " ";
    int WS_CNT = 0;
    int WS_I = 0;
    int WS_PRIN_SQE = 0;
    short WS_CARD_SEQ = 0;
    short WS_CURR_PAGE = 0;
    char WS_LAST_PAGE = ' ';
    int WS_CON_NUM = 0;
    int WS_TOTAL_NUM = 0;
    int WS_NEXT_START_NUM = 0;
    int WS_BAL_CNT = 0;
    char WS_FIND_CCY = ' ';
    DCZIYJUP_WS_OUTPUT_DATA WS_OUTPUT_DATA = new DCZIYJUP_WS_OUTPUT_DATA();
    String WS_SECRET_KEY = " ";
    String WS_PRINT_PSW = " ";
    String WS_DEL_PSW = " ";
    char WS_ADSC_TYPE = ' ';
    String WS_YJ_AC = " ";
    char WS_TBL_FLAG = ' ';
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCMSG SCCMSG = new SCCMSG();
    DCCUSPAC DCCUSPAC = new DCCUSPAC();
    DCRSSTS DCRSSTS = new DCRSSTS();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    DCCIYJUP DCCIYJUP;
    public void MP(SCCGWA SCCGWA, DCCIYJUP DCCIYJUP) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DCCIYJUP = DCCIYJUP;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DCZIYJUP return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, DCCUSPAC);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        B020_GET_STD_AC();
        if (pgmRtn) return;
        B030_UPDATE_STS_DATA();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DCCIYJUP.IN_DATA.AC);
        if (DCCIYJUP.IN_DATA.AC.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, DDCMSG_ERROR_MSG.DD_AC_NO_M_INPUT, DCCIYJUP.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, DCCIYJUP.IN_DATA.CCY);
        CEP.TRC(SCCGWA, DCCIYJUP.IN_DATA.CCY_TYPE);
        CEP.TRC(SCCGWA, DCCIYJUP.IN_DATA.STS);
        if (DCCIYJUP.IN_DATA.STS != '1' 
            && DCCIYJUP.IN_DATA.STS != '2' 
            && DCCIYJUP.IN_DATA.STS != '3') {
            IBS.CPY2CLS(SCCGWA, DDCMSG_ERROR_MSG.DD_STS_NO_INVALID, DCCIYJUP.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B020_GET_STD_AC() throws IOException,SQLException,Exception {
        S000_CALL_DCZUSPAC();
        if (pgmRtn) return;
        if (DCCUSPAC.OUTPUT.AC_TYPE == '0') {
            WS_YJ_AC = DCCUSPAC.OUTPUT.STD_AC;
        } else if ((DCCUSPAC.OUTPUT.AC_TYPE == '1' 
                || DCCUSPAC.OUTPUT.AC_TYPE == '2')) {
            WS_YJ_AC = DCCUSPAC.FUNC.AC;
        } else {
            WS_YJ_AC = DCCUSPAC.FUNC.AC;
        }
    }
    public void B030_UPDATE_STS_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCRSSTS);
        DCRSSTS.KEY.AC = WS_YJ_AC;
        DCRSSTS.KEY.CCY = DCCIYJUP.IN_DATA.CCY;
        DCRSSTS.KEY.CCY_TYPE = DCCIYJUP.IN_DATA.CCY_TYPE;
        WS_TOTAL_NUM = 0;
        T000_STARTBR_DCTSSTS();
        if (pgmRtn) return;
        T000_READNEXT_DCTSSTS();
        if (pgmRtn) return;
        while (WS_TBL_FLAG != 'N') {
            B031_UPDATE_DCTSSTS();
            if (pgmRtn) return;
            T000_READNEXT_DCTSSTS();
            if (pgmRtn) return;
        }
        if (WS_TOTAL_NUM == 0) {
            IBS.CPY2CLS(SCCGWA, DDCMSG_ERROR_MSG.DD_AC_NOT_FOUND, DCCIYJUP.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B031_UPDATE_DCTSSTS() throws IOException,SQLException,Exception {
        T000_READUP_DCTSSTS();
        if (pgmRtn) return;
        DCRSSTS.STS = DCCIYJUP.IN_DATA.STS;
        DCRSSTS.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DCRSSTS.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        T000_REWRITE_DCTSSTS();
        if (pgmRtn) return;
    }
    public void T000_STARTBR_DCTSSTS() throws IOException,SQLException,Exception {
        DCTSSTS_BR.rp = new DBParm();
        DCTSSTS_BR.rp.TableName = "DCTSSTS";
        DCTSSTS_BR.rp.where = "AC = :DCRSSTS.KEY.AC";
        IBS.STARTBR(SCCGWA, DCRSSTS, this, DCTSSTS_BR);
    }
    public void T000_READNEXT_DCTSSTS() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, DCRSSTS, this, DCTSSTS_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CEP.TRC(SCCGWA, "DATA FOUND");
            WS_TBL_FLAG = 'Y';
            WS_TOTAL_NUM = WS_TOTAL_NUM + 1;
        }
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.TRC(SCCGWA, "DATA NOT FOUND");
            WS_TBL_FLAG = 'N';
        }
    }
    public void T000_READUP_DCTSSTS() throws IOException,SQLException,Exception {
        DCTSSTS_RD = new DBParm();
        DCTSSTS_RD.TableName = "DCTSSTS";
        DCTSSTS_RD.upd = true;
        IBS.READ(SCCGWA, DCRSSTS, DCTSSTS_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TBL_FLAG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DCTSSTS";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_REWRITE_DCTSSTS() throws IOException,SQLException,Exception {
        DCTSSTS_RD = new DBParm();
        DCTSSTS_RD.TableName = "DCTSSTS";
        IBS.REWRITE(SCCGWA, DCRSSTS, DCTSSTS_RD);
    }
    public void T000_ENDBR_DCTSSTS() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, DCTSSTS_BR);
    }
    public void S000_CALL_DCZUSPAC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCUSPAC);
        DCCUSPAC.FUNC.AC = DCCIYJUP.IN_DATA.AC;
        IBS.CALLCPN(SCCGWA, "DC-INQ-STD-AC", DCCUSPAC);
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
