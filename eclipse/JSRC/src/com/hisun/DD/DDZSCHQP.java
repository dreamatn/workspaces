package com.hisun.DD;

import com.hisun.DC.*;
import com.hisun.SC.*;
import com.hisun.CI.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class DDZSCHQP {
    DBParm DDTCHQP_RD;
    brParm DDTCHQP_BR = new brParm();
    DBParm DDTMST_RD;
    DBParm DCTIAMST_RD;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    int WS_TOTAL_NUM = 0;
    int WS_RCD_SEQ = 0;
    String K_OBJ_SYSTEM = "ZFMM";
    String K_SERV_CODE = "ZFX104";
    String K_OUTPUT_FMT_A = "DD536";
    int WS_SCHQP_STR_DT = 0;
    int WS_SCHQP_STOP_DT = 0;
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    DDZSCHQP_WS_DATA_TO_SCZTPCL WS_DATA_TO_SCZTPCL = new DDZSCHQP_WS_DATA_TO_SCZTPCL();
    short WS_TOTAL_PAGE = 0;
    short WS_REMAINDER = 0;
    short WS_CNT = 0;
    char WS_CHQP_FLG = ' ';
    char WS_CHQP_BRFLG = ' ';
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCTPCL SCCTPCL = new SCCTPCL();
    DDRCHQP DDRCHQP = new DDRCHQP();
    DDRMST DDRMST = new DDRMST();
    DDCOCHQP DDCOCHQP = new DDCOCHQP();
    DCCPACTY DCCPACTY = new DCCPACTY();
    DCCUSPAC DCCUSPAC = new DCCUSPAC();
    DCRIAMST DCRIAMST = new DCRIAMST();
    CICQACAC CICQACAC = new CICQACAC();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    DDCSCHQP DDCSCHQP;
    public void MP(SCCGWA SCCGWA, DDCSCHQP DDCSCHQP) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DDCSCHQP = DDCSCHQP;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DDZSCHQP return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B001_CHECK_INPUT();
        if (pgmRtn) return;
        B010_GET_TOTAL_NUM();
        if (pgmRtn) return;
        T000_STARTBR_DDTCHQP_PROC();
        if (pgmRtn) return;
        B020_PROC_MAIN();
        if (pgmRtn) return;
        T000_ENDBR_DDTCHQP_PROC();
        if (pgmRtn) return;
    }
    public void B001_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDCSCHQP.AC);
        IBS.init(SCCGWA, CICQACAC);
        if (DDCSCHQP.AC.trim().length() > 0) {
            CICQACAC.FUNC = 'R';
            CICQACAC.DATA.AGR_NO = DDCSCHQP.AC;
            S000_CALL_CIZQACAC();
            if (pgmRtn) return;
            if (CICQACAC.O_DATA.O_ACR_DATA.O_FRM_APP_ACR.equalsIgnoreCase("DD")) {
                IBS.init(SCCGWA, DDRMST);
                DDRMST.KEY.CUS_AC = DDCSCHQP.AC;
                T000_READ_DDTMST();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, DDRMST.AC_STS);
            }
        }
    }
    public void B010_GET_TOTAL_NUM() throws IOException,SQLException,Exception {
        WS_TOTAL_PAGE = 0;
        WS_TOTAL_NUM = 0;
        WS_REMAINDER = 0;
        IBS.init(SCCGWA, DDRCHQP);
        DDRCHQP.KEY.AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
        CEP.TRC(SCCGWA, DDCSCHQP.STR_DT);
        CEP.TRC(SCCGWA, DDCSCHQP.STOP_DT);
        CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO);
        CEP.TRC(SCCGWA, DDRCHQP.KEY.AC);
        CEP.TRC(SCCGWA, DDCSCHQP.PAGE_NUM);
        CEP.TRC(SCCGWA, DDCSCHQP.PAGE_ROW);
        if (DDCSCHQP.PAGE_ROW == 0) {
            DDCSCHQP.PAGE_ROW = 20;
        }
        if (DDCSCHQP.PAGE_NUM == 0) {
            CEP.TRC(SCCGWA, "111111");
            WS_RCD_SEQ = 1;
        } else {
            CEP.TRC(SCCGWA, "2222222222");
            WS_RCD_SEQ = ( DDCSCHQP.PAGE_NUM - 1 ) * DDCSCHQP.PAGE_ROW + 1;
        }
        if (DDRCHQP.KEY.AC.trim().length() > 0) {
            CEP.TRC(SCCGWA, "222222222222222222");
            WS_CHQP_BRFLG = 'B';
            DDTCHQP_RD = new DBParm();
            DDTCHQP_RD.TableName = "DDTCHQP";
            DDTCHQP_RD.set = "WS-TOTAL-NUM=COUNT(*)";
            DDTCHQP_RD.where = "AC = :CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO";
            IBS.GROUP(SCCGWA, DDRCHQP, this, DDTCHQP_RD);
        } else if (DDRCHQP.KEY.AC.trim().length() == 0 
                && DDCSCHQP.STR_DT != 0 
                && DDCSCHQP.STOP_DT != 0) {
            CEP.TRC(SCCGWA, "33333333333333333333333");
            WS_CHQP_BRFLG = 'C';
            WS_SCHQP_STR_DT = DDCSCHQP.STR_DT;
            WS_SCHQP_STOP_DT = DDCSCHQP.STOP_DT;
            DDTCHQP_RD = new DBParm();
            DDTCHQP_RD.TableName = "DDTCHQP";
            DDTCHQP_RD.set = "WS-TOTAL-NUM=COUNT(*)";
            DDTCHQP_RD.where = "STR_DT >= :WS_SCHQP_STR_DT "
                + "AND STR_DT <= :WS_SCHQP_STOP_DT";
            IBS.GROUP(SCCGWA, DDRCHQP, this, DDTCHQP_RD);
        } else {
        }
        CEP.TRC(SCCGWA, WS_TOTAL_NUM);
        WS_REMAINDER = (short) (WS_TOTAL_NUM % DDCSCHQP.PAGE_ROW);
        WS_TOTAL_PAGE = (short) ((WS_TOTAL_NUM - WS_REMAINDER) / DDCSCHQP.PAGE_ROW);
        CEP.TRC(SCCGWA, WS_TOTAL_NUM);
        CEP.TRC(SCCGWA, WS_REMAINDER);
        if (WS_REMAINDER != 0) {
            WS_TOTAL_PAGE += 1;
        }
        CEP.TRC(SCCGWA, WS_TOTAL_NUM);
    }
    public void B020_PROC_MAIN() throws IOException,SQLException,Exception {
        T000_READNEXT_DDTCHQP_PROC();
        if (pgmRtn) return;
        if (WS_CHQP_FLG == 'N') {
            if (DDCSCHQP.PAGE_NUM == 0) {
                CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_NO_RSLT);
            } else {
                CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_LAST_PAGE);
            }
        }
        for (WS_CNT = 1; WS_CHQP_FLG != 'N' 
            && WS_CNT <= DDCSCHQP.PAGE_ROW; WS_CNT += 1) {
            if (WS_CHQP_BRFLG == 'C') {
                IBS.init(SCCGWA, CICQACAC);
                CICQACAC.FUNC = 'A';
                CICQACAC.DATA.ACAC_NO = DDRCHQP.KEY.AC;
                S000_CALL_CIZQACAC();
                if (pgmRtn) return;
                DDCOCHQP.OUTPUT_DATA[WS_CNT-1].AC = CICQACAC.O_DATA.O_ACR_DATA.O_AGR_NO;
            } else {
                DDCOCHQP.OUTPUT_DATA[WS_CNT-1].AC = DDCSCHQP.AC;
            }
            DDCOCHQP.OUTPUT_DATA[WS_CNT-1].AC_NAME = CICQACAC.O_DATA.O_ACR_DATA.O_AC_CNM;
            CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACR_DATA.O_AC_CNM);
            DDCOCHQP.OUTPUT_DATA[WS_CNT-1].PASS_NO = DDRCHQP.KEY.PASS_NO;
            DDCOCHQP.OUTPUT_DATA[WS_CNT-1].STS = DDRCHQP.KEY.STS;
            DDCOCHQP.OUTPUT_DATA[WS_CNT-1].STR_DT = DDRCHQP.KEY.STR_DT;
            DDCOCHQP.OUTPUT_DATA[WS_CNT-1].STOP_DT = DDRCHQP.STOP_DT;
            CEP.TRC(SCCGWA, DDRCHQP.KEY.AC);
            CEP.TRC(SCCGWA, DDRCHQP.KEY.PASS_NO);
            CEP.TRC(SCCGWA, DDRCHQP.KEY.STS);
            CEP.TRC(SCCGWA, DDRCHQP.KEY.STR_DT);
            CEP.TRC(SCCGWA, DDRCHQP.STOP_DT);
            WS_RCD_SEQ = WS_RCD_SEQ + 1;
            T000_READNEXT_DDTCHQP_PROC();
            if (pgmRtn) return;
        }
        if (DDCSCHQP.PAGE_NUM == 0) {
            DDCOCHQP.TOT_PAGE = WS_TOTAL_PAGE;
            DDCOCHQP.TOT_NUM = (short) WS_TOTAL_NUM;
            DDCOCHQP.CURR_PAGE = 1;
        } else {
            DDCOCHQP.CURR_PAGE = DDCSCHQP.PAGE_NUM;
        }
        if (WS_CHQP_FLG == 'N' 
            || WS_CNT > DDCSCHQP.PAGE_ROW) {
            DDCOCHQP.LAST_IND = 'Y';
            DDCOCHQP.CURR_ROW = (short) (WS_CNT - 1);
        } else {
            DDCOCHQP.LAST_IND = 'N';
            DDCOCHQP.CURR_ROW = DDCSCHQP.PAGE_ROW;
        }
        CEP.TRC(SCCGWA, DDCOCHQP.TOT_PAGE);
        CEP.TRC(SCCGWA, DDCOCHQP.TOT_NUM);
        CEP.TRC(SCCGWA, DDCOCHQP.CURR_PAGE);
        CEP.TRC(SCCGWA, DDCOCHQP.CURR_ROW);
        CEP.TRC(SCCGWA, DDCOCHQP.LAST_IND);
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT_A;
        SCCFMT.DATA_PTR = DDCOCHQP;
        SCCFMT.DATA_LEN = 6257;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void R000_OPEN_MPAGE_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.TITL = " ";
        SCCMPAG.SUBT_ROW_CNT = 0;
        SCCMPAG.MAX_COL_NO = 6257;
        if ("10".trim().length() == 0) SCCMPAG.SCR_ROW_CNT = 0;
        else SCCMPAG.SCR_ROW_CNT = Short.parseShort("10");
        SCCMPAG.SCR_COL_CNT = 0;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void R000_OUTPUT_DETAIL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, DDCOCHQP);
        SCCMPAG.DATA_LEN = 6257;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void T000_STARTBR_DDTCHQP_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRCHQP);
        DDRCHQP.KEY.AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
        CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO);
        CEP.TRC(SCCGWA, DDRCHQP.KEY.AC);
        if (WS_CHQP_BRFLG == 'A') {
            WS_SCHQP_STR_DT = DDCSCHQP.STR_DT;
            WS_SCHQP_STOP_DT = DDCSCHQP.STOP_DT;
            DDTCHQP_BR.rp = new DBParm();
            DDTCHQP_BR.rp.TableName = "DDTCHQP";
            DDTCHQP_BR.rp.where = "AC = :CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO "
                + "AND STR_DT >= :WS_SCHQP_STR_DT "
                + "AND STR_DT <= :WS_SCHQP_STOP_DT";
            DDTCHQP_BR.rp.order = "STR_DT DESC";
            IBS.STARTBR(SCCGWA, DDRCHQP, this, DDTCHQP_BR);
        } else if (WS_CHQP_BRFLG == 'B') {
            DDTCHQP_BR.rp = new DBParm();
            DDTCHQP_BR.rp.TableName = "DDTCHQP";
            DDTCHQP_BR.rp.where = "AC = :CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO";
            IBS.STARTBR(SCCGWA, DDRCHQP, this, DDTCHQP_BR);
        } else if (WS_CHQP_BRFLG == 'C') {
            WS_SCHQP_STR_DT = DDCSCHQP.STR_DT;
            WS_SCHQP_STOP_DT = DDCSCHQP.STOP_DT;
            DDTCHQP_BR.rp = new DBParm();
            DDTCHQP_BR.rp.TableName = "DDTCHQP";
            DDTCHQP_BR.rp.where = "STR_DT >= :WS_SCHQP_STR_DT "
                + "AND STR_DT <= :WS_SCHQP_STOP_DT";
            DDTCHQP_BR.rp.order = "STR_DT DESC";
            IBS.STARTBR(SCCGWA, DDRCHQP, this, DDTCHQP_BR);
        } else {
            CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_INPUT_ERR);
        }
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_CHQP_FLG = 'Y';
        } else {
            WS_CHQP_FLG = 'N';
        }
    }
    public void T000_READNEXT_DDTCHQP_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_RCD_SEQ);
        IBS.READNEXT(SCCGWA, DDRCHQP, this, DDTCHQP_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_CHQP_FLG = 'Y';
        } else {
            WS_CHQP_FLG = 'N';
        }
    }
    public void T000_ENDBR_DDTCHQP_PROC() throws IOException,SQLException,Exception {
        DDTCHQP_BR.rp.errhdl = true;
        IBS.ENDBR(SCCGWA, DDTCHQP_BR);
    }
    public void S000_CALL_DCZPACTY() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-INQ-AC-INF", DCCPACTY);
        CEP.TRC(SCCGWA, DCCPACTY.RC);
        if (DCCPACTY.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DCCPACTY.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_CIZQACAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACAC", CICQACAC);
        CEP.TRC(SCCGWA, CICQACAC.RC);
        if (CICQACAC.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICQACAC.RC);
        }
    }
    public void T000_READ_DDTMST() throws IOException,SQLException,Exception {
        DDTMST_RD = new DBParm();
        DDTMST_RD.TableName = "DDTMST";
        IBS.READ(SCCGWA, DDRMST, DDTMST_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_MST1_REC_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_DCTIAMST() throws IOException,SQLException,Exception {
        DCTIAMST_RD = new DBParm();
        DCTIAMST_RD.TableName = "DCTIAMST";
        DCTIAMST_RD.col = "VIA_AC,PRD_TYPE,AC_STS";
        IBS.READ(SCCGWA, DCRIAMST, DCTIAMST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_VIA_MST_RCD_NOT_FND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
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
