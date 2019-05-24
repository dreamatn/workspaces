package com.hisun.LN;

import com.hisun.SC.*;
import com.hisun.BP.*;
import com.hisun.DB.*;
import com.hisun.SM.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class LNZSRATH {
    int JIBS_tmp_int;
    brParm LNTRATH_BR = new brParm();
    DBParm LNTRATN_RD;
    DBParm LNTRATL_RD;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    short Q_MAX_CNT = 1000;
    String FMT_ID = "LN805";
    LNZSRATH_WS_DB_VARS WS_DB_VARS = new LNZSRATH_WS_DB_VARS();
    LNZSRATH_WS_VARIABLES WS_VARIABLES = new LNZSRATH_WS_VARIABLES();
    LNZSRATH_WS_OUT_RECODE WS_OUT_RECODE = new LNZSRATH_WS_OUT_RECODE();
    LNZSRATH_WS_PAGE_INFO WS_PAGE_INFO = new LNZSRATH_WS_PAGE_INFO();
    LNZSRATH_WS_COND_FLG WS_COND_FLG = new LNZSRATH_WS_COND_FLG();
    LNRRATH LNRRATH = new LNRRATH();
    LNRRATN LNRRATN = new LNRRATN();
    LNRRATL LNRRATL = new LNRRATL();
    LNCMSG_ERROR_MSG ERROR_MSG = new LNCMSG_ERROR_MSG();
    SCCWOUT SCCWOUT = new SCCWOUT();
    BPRPARP BPRPARP = new BPRPARP();
    BPRPARM BPRPARM = new BPRPARM();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCFMT SCCFMT = new SCCFMT();
    LNCIPART LNCIPART = new LNCIPART();
    LNCRRATH LNCRRATH = new LNCRRATH();
    int RTCD = 0;
    SCCGWA SCCGWA;
    SCCGBPA_BP_AREA BP_AREA;
    SCCGCPT SCCGCPT;
    BPRTRT BPRTRT;
    LNCSRATH LNCSRATH;
    public void MP(SCCGWA SCCGWA, LNCSRATH LNCSRATH) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.LNCSRATH = LNCSRATH;
        CEP.TRC(SCCGWA);
        A00_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "LNZSRATH return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A00_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_VARIABLES.WS_TEMP_VARIABLE);
        IBS.init(SCCGWA, WS_COND_FLG);
        IBS.init(SCCGWA, WS_DB_VARS);
        IBS.init(SCCGWA, SCCFMT);
        IBS.init(SCCGWA, WS_PAGE_INFO);
        IBS.init(SCCGWA, WS_OUT_RECODE.WS_OUT_HEAD);
        CEP.TRC(SCCGWA, LNCSRATH.INT_TYP);
        CEP.TRC(SCCGWA, LNCSRATH.CONTRACT_NO);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (LNCSRATH.SRT_DT == 0) {
            LNCSRATH.SRT_DT = 101;
        }
        if (LNCSRATH.MAT_DT == 0) {
            LNCSRATH.MAT_DT = 99991231;
        }
        WS_DB_VARS.S_DT = LNCSRATH.SRT_DT;
        WS_DB_VARS.D_DT = LNCSRATH.MAT_DT;
        B001_GET_PAGE_INFO();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, LNCSRATH.PAGE_ROW);
        CEP.TRC(SCCGWA, LNCSRATH.PAGE_ROW);
        if (LNCSRATH.CI_NO.trim().length() > 0) {
            B101_STBR_WITH_SUB_CTA();
            if (pgmRtn) return;
        } else {
            B102_STARTBR_LNTRATH();
            if (pgmRtn) return;
        }
        WS_COND_FLG.EOF_FLG = 'N';
        T000_READNEXT_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, WS_COND_FLG.EOF_FLG);
        WS_VARIABLES.TS_CNT += 1;
        while (WS_COND_FLG.EOF_FLG != 'Y') {
            WS_PAGE_INFO.TOTAL_NUM += 1;
            CEP.TRC(SCCGWA, WS_PAGE_INFO.PAGE_IDX);
            CEP.TRC(SCCGWA, WS_PAGE_INFO.PAGE_ROW);
            CEP.TRC(SCCGWA, WS_PAGE_INFO.TOTAL_NUM);
            CEP.TRC(SCCGWA, WS_PAGE_INFO.NEXT_START_NUM);
            if (WS_PAGE_INFO.PAGE_IDX < WS_PAGE_INFO.PAGE_ROW 
                && WS_PAGE_INFO.TOTAL_NUM >= WS_PAGE_INFO.NEXT_START_NUM) {
                CEP.TRC(SCCGWA, "11111111111111111111111111111111111");
                WS_PAGE_INFO.ROW_FLG = 'Y';
                WS_PAGE_INFO.PAGE_IDX += 1;
                IBS.init(SCCGWA, WS_OUT_RECODE.WS_OUT_DATA[WS_PAGE_INFO.PAGE_IDX-1]);
                WS_OUT_RECODE.WS_OUT_DATA[WS_PAGE_INFO.PAGE_IDX-1].OUT_CTA_NO = LNRRATH.KEY.CONTRACT_NO;
                WS_OUT_RECODE.WS_OUT_DATA[WS_PAGE_INFO.PAGE_IDX-1].OUT_SUB_CTA_NO = LNRRATH.KEY.SUB_CTA_NO;
                WS_OUT_RECODE.WS_OUT_DATA[WS_PAGE_INFO.PAGE_IDX-1].OUT_RATE_TYP = LNRRATH.KEY.RATE_TYP;
                WS_OUT_RECODE.WS_OUT_DATA[WS_PAGE_INFO.PAGE_IDX-1].OUT_RATE_CHG_DT = LNRRATH.KEY.RAT_CHG_DT;
                WS_OUT_RECODE.WS_OUT_DATA[WS_PAGE_INFO.PAGE_IDX-1].OUT_RATE_KIND = LNRRATH.RATE_KIND;
                WS_OUT_RECODE.WS_OUT_DATA[WS_PAGE_INFO.PAGE_IDX-1].OUT_INT_RATE = LNRRATH.INT_RAT;
                CEP.TRC(SCCGWA, WS_OUT_RECODE.WS_OUT_DATA[WS_PAGE_INFO.PAGE_IDX-1].OUT_CTA_NO);
                CEP.TRC(SCCGWA, WS_OUT_RECODE.WS_OUT_DATA[WS_PAGE_INFO.PAGE_IDX-1].OUT_SUB_CTA_NO);
                CEP.TRC(SCCGWA, WS_OUT_RECODE.WS_OUT_DATA[WS_PAGE_INFO.PAGE_IDX-1].OUT_RATE_TYP);
                CEP.TRC(SCCGWA, WS_OUT_RECODE.WS_OUT_DATA[WS_PAGE_INFO.PAGE_IDX-1].OUT_RATE_CHG_DT);
                CEP.TRC(SCCGWA, WS_OUT_RECODE.WS_OUT_DATA[WS_PAGE_INFO.PAGE_IDX-1].OUT_RATE_KIND);
                CEP.TRC(SCCGWA, WS_OUT_RECODE.WS_OUT_DATA[WS_PAGE_INFO.PAGE_IDX-1].OUT_INT_RATE);
            }
            CEP.TRC(SCCGWA, WS_VARIABLES.TS_REC);
            T000_READNEXT_PROC();
            if (pgmRtn) return;
        }
        T000_ENDBR_PROC();
        if (pgmRtn) return;
        B003_OUTPUT_DATA_BEGIN();
        if (pgmRtn) return;
        B025_OUT_RECORD();
        if (pgmRtn) return;
    }
    public void B100_GET_SUBCTA_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCIPART.DATA);
        LNCIPART.DATA.CONTRACT_NO = LNCSRATH.CONTRACT_NO;
        LNCIPART.DATA.CI_NO_IN = LNCSRATH.CI_NO;
        LNCIPART.DATA.LEVEL = 'R';
        LNCIPART.DATA.FUNC = 'S';
        S000_CALL_LNZIPART();
        if (pgmRtn) return;
        LNCSRATH.SUB_CTA = "" + LNCIPART.GROUP.get(1-1).SEQ_NO;
        JIBS_tmp_int = LNCSRATH.SUB_CTA.length();
        for (int i=0;i<8-JIBS_tmp_int;i++) LNCSRATH.SUB_CTA = "0" + LNCSRATH.SUB_CTA;
        CEP.TRC(SCCGWA, LNCSRATH.SUB_CTA);
    }
    public void B101_STBR_WITH_SUB_CTA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRRATH);
        IBS.init(SCCGWA, LNCRRATH);
        if (LNCSRATH.INT_TYP == ' ') {
            CEP.TRC(SCCGWA, "STBR BY KEY1");
            LNRRATH.KEY.CONTRACT_NO = LNCSRATH.CONTRACT_NO;
            if (LNCSRATH.SUB_CTA.trim().length() == 0) LNRRATH.KEY.SUB_CTA_NO = 0;
            else LNRRATH.KEY.SUB_CTA_NO = Integer.parseInt(LNCSRATH.SUB_CTA);
            LNCRRATH.FUNC = 'B';
            LNCRRATH.OPT = 'B';
            S000_CALL_LNZRRATH();
            if (pgmRtn) return;
        } else {
            CEP.TRC(SCCGWA, "STBR BY KEY3");
            LNRRATH.KEY.CONTRACT_NO = LNCSRATH.CONTRACT_NO;
            if (LNCSRATH.SUB_CTA.trim().length() == 0) LNRRATH.KEY.SUB_CTA_NO = 0;
            else LNRRATH.KEY.SUB_CTA_NO = Integer.parseInt(LNCSRATH.SUB_CTA);
            LNRRATH.KEY.RATE_TYP = LNCSRATH.INT_TYP;
            LNCRRATH.FUNC = 'B';
            LNCRRATH.OPT = 'Z';
            S000_CALL_LNZRRATH();
            if (pgmRtn) return;
        }
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CEP.TRC(SCCGWA, "STARTBR LNTRATH NORMAL");
        } else {
            WS_VARIABLES.WS_TEMP_VARIABLE.MSGID = ERROR_MSG.LN_ERR_SYS_ERR;
            CEP.ERR(SCCGWA, WS_VARIABLES.WS_TEMP_VARIABLE.MSGID);
        }
    }
    public void B102_STARTBR_LNTRATH() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, LNCSRATH.SRT_DT);
        CEP.TRC(SCCGWA, LNCSRATH.MAT_DT);
        IBS.init(SCCGWA, LNRRATH);
        IBS.init(SCCGWA, LNCRRATH);
        if (LNCSRATH.INT_TYP == ' ') {
            CEP.TRC(SCCGWA, "STBR BY CTANO");
            LNRRATH.KEY.CONTRACT_NO = LNCSRATH.CONTRACT_NO;
            LNRRATH.KEY.SUB_CTA_NO = 0;
            CEP.TRC(SCCGWA, LNRRATH.KEY.CONTRACT_NO);
            CEP.TRC(SCCGWA, LNRRATH.KEY.SUB_CTA_NO);
            CEP.TRC(SCCGWA, WS_DB_VARS.S_DT);
            CEP.TRC(SCCGWA, WS_DB_VARS.D_DT);
            T000_STARTBR_CTANO_PROC();
            if (pgmRtn) return;
        } else {
            CEP.TRC(SCCGWA, "STBR BY KEY2");
            LNRRATH.KEY.CONTRACT_NO = LNCSRATH.CONTRACT_NO;
            LNRRATH.KEY.RATE_TYP = LNCSRATH.INT_TYP;
            T000_STARTBR_BY_KEY2_PROC();
            if (pgmRtn) return;
        }
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CEP.TRC(SCCGWA, "STARTBR LNTRATH NORMAL");
        } else {
            CEP.TRC(SCCGWA, "SHICHECK");
            WS_VARIABLES.WS_TEMP_VARIABLE.MSGID = ERROR_MSG.LN_ERR_SYS_ERR;
            CEP.ERR(SCCGWA, WS_VARIABLES.WS_TEMP_VARIABLE.MSGID);
        }
    }
    public void B103_READNEXT_LNTRATH() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRRATH);
        IBS.init(SCCGWA, LNCRRATH);
        LNCRRATH.FUNC = 'B';
        LNCRRATH.OPT = 'R';
        S000_CALL_LNZRRATH();
        if (pgmRtn) return;
        if (LNCRRATH.RETURN_INFO == 'E' 
                && WS_VARIABLES.TS_CNT == 0) {
            WS_VARIABLES.WS_TEMP_VARIABLE.MSGID = ERROR_MSG.LN_ERR_RATH_NOTFND;
            CEP.ERR(SCCGWA, WS_VARIABLES.WS_TEMP_VARIABLE.MSGID);
        } else if (LNCRRATH.RETURN_INFO == 'E' 
                && WS_VARIABLES.TS_CNT != 0) {
            WS_COND_FLG.EOF_FLG = 'Y';
        } else if (LNCRRATH.RETURN_INFO == 'F') {
            CEP.TRC(SCCGWA, "READNEXT LNTRATH NORMAL");
        } else {
            WS_VARIABLES.WS_TEMP_VARIABLE.MSGID = ERROR_MSG.LN_ERR_SYS_ERR;
            CEP.ERR(SCCGWA, WS_VARIABLES.WS_TEMP_VARIABLE.MSGID);
        }
    }
    public void B104_ENDBR_LNTRATH() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRRATH);
        IBS.init(SCCGWA, LNCRRATH);
        LNCRRATH.FUNC = 'B';
        LNCRRATH.OPT = 'E';
        S000_CALL_LNZRRATH();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else {
            WS_VARIABLES.WS_TEMP_VARIABLE.MSGID = ERROR_MSG.LN_ERR_SYS_ERR;
            CEP.ERR(SCCGWA, WS_VARIABLES.WS_TEMP_VARIABLE.MSGID);
        }
    }
    public void B025_OUT_RECORD() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_VARIABLES.TS_REC);
        WS_VARIABLES.LEN = (short) 39375;
        WS_VARIABLES.TS_CNT += 1;
        if (WS_VARIABLES.TS_CNT <= Q_MAX_CNT) {
            S000_WRITE_TS();
            if (pgmRtn) return;
        } else {
            WS_VARIABLES.TS_REC = " ";
            WS_VARIABLES.TS_REC = "TO BE CONTINUED";
            WS_VARIABLES.LEN = (short) 39375;
            S000_WRITE_TS();
            if (pgmRtn) return;
            WS_COND_FLG.EOF_FLG = 'Y';
        }
    }
    public void S000_WRITE_TS() throws IOException,SQLException,Exception {
        if (WS_VARIABLES.TS_CNT > Q_MAX_CNT) {
            WS_VARIABLES.WS_TEMP_VARIABLE.MSGID = ERROR_MSG.LN_REC_NUM_EXCEED;
            CEP.ERR(SCCGWA, WS_VARIABLES.WS_TEMP_VARIABLE.MSGID);
        }
        SCCFMT.FMTID = FMT_ID;
        SCCFMT.DATA_PTR = WS_OUT_RECODE;
        SCCFMT.DATA_LEN = 1594;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void B001_GET_PAGE_INFO() throws IOException,SQLException,Exception {
        WS_PAGE_INFO.ROW_FLG = 'N';
        WS_PAGE_INFO.TOTAL_NUM = 0;
        WS_PAGE_INFO.PAGE_IDX = 0;
        WS_PAGE_INFO.LAST_PAGE = 'N';
        WS_PAGE_INFO.PAGE_ROW = LNCSRATH.PAGE_ROW;
        WS_PAGE_INFO.CURR_PAGE = (short) LNCSRATH.PAGE_NUM;
        WS_PAGE_INFO.NEXT_START_NUM = ( ( WS_PAGE_INFO.CURR_PAGE - 1 ) * WS_PAGE_INFO.PAGE_ROW ) + 1;
    }
    public void S000_CALL_LNZIPART() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-UNT-PARTI-INQ", LNCIPART);
        if (LNCIPART.RC.RC_CODE != 0) {
            WS_VARIABLES.WS_TEMP_VARIABLE.MSGID = IBS.CLS2CPY(SCCGWA, LNCIPART.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZRRATH() throws IOException,SQLException,Exception {
        LNCRRATH.REC_PTR = LNRRATH;
        LNCRRATH.REC_LEN = 1311;
        IBS.CALLCPN(SCCGWA, "LN-RSC-LNTRATH", LNCRRATH);
    }
    public void T000_STARTBR_CTANO_PROC() throws IOException,SQLException,Exception {
        LNTRATH_BR.rp = new DBParm();
        LNTRATH_BR.rp.TableName = "LNTRATH";
        LNTRATH_BR.rp.where = "CONTRACT_NO = :LNRRATH.KEY.CONTRACT_NO "
            + "AND SUB_CTA_NO = :LNRRATH.KEY.SUB_CTA_NO "
            + "AND RAT_CHG_DT >= :WS_DB_VARS.S_DT "
            + "AND RAT_CHG_DT <= :WS_DB_VARS.D_DT";
        LNTRATH_BR.rp.order = "RATE_TYP, RAT_CHG_DT";
        IBS.STARTBR(SCCGWA, LNRRATH, this, LNTRATH_BR);
    }
    public void T000_STARTBR_BY_KEY2_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, LNRRATH.KEY.CONTRACT_NO);
        CEP.TRC(SCCGWA, LNRRATH.KEY.SUB_CTA_NO);
        CEP.TRC(SCCGWA, LNRRATH.KEY.RATE_TYP);
        CEP.TRC(SCCGWA, WS_DB_VARS.S_DT);
        CEP.TRC(SCCGWA, WS_DB_VARS.D_DT);
        LNTRATH_BR.rp = new DBParm();
        LNTRATH_BR.rp.TableName = "LNTRATH";
        LNTRATH_BR.rp.where = "CONTRACT_NO = :LNRRATH.KEY.CONTRACT_NO "
            + "AND SUB_CTA_NO = :LNRRATH.KEY.SUB_CTA_NO "
            + "AND RATE_TYP = :LNRRATH.KEY.RATE_TYP "
            + "AND RAT_CHG_DT >= :WS_DB_VARS.S_DT "
            + "AND RAT_CHG_DT <= :WS_DB_VARS.D_DT";
        LNTRATH_BR.rp.order = "RAT_CHG_DT";
        IBS.STARTBR(SCCGWA, LNRRATH, this, LNTRATH_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
    }
    public void T000_READNEXT_PROC() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, LNRRATH, this, LNTRATH_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_COND_FLG.EOF_FLG = 'Y';
        }
    }
    public void T000_ENDBR_PROC() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, LNTRATH_BR);
    }
    public void T000_READ_RATN() throws IOException,SQLException,Exception {
        LNTRATN_RD = new DBParm();
        LNTRATN_RD.TableName = "LNTRATN";
        LNTRATN_RD.where = "CONTRACT_NO = :LNRRATN.KEY.CONTRACT_NO "
            + "AND ACTV_DT <= :LNRRATN.KEY.ACTV_DT";
        LNTRATN_RD.fst = true;
        LNTRATN_RD.order = "ACTV_DT DESC";
        IBS.READ(SCCGWA, LNRRATN, this, LNTRATN_RD);
    }
    public void T000_READ_RATL() throws IOException,SQLException,Exception {
        LNTRATL_RD = new DBParm();
        LNTRATL_RD.TableName = "LNTRATL";
        LNTRATL_RD.where = "CONTRACT_NO = :LNRRATL.KEY.CONTRACT_NO "
            + "AND ACTV_DT <= :LNRRATL.KEY.ACTV_DT "
            + "AND OVD_KND = :LNRRATL.KEY.OVD_KND";
        LNTRATL_RD.fst = true;
        LNTRATL_RD.order = "ACTV_DT DESC";
        IBS.READ(SCCGWA, LNRRATL, this, LNTRATL_RD);
    }
    public void B003_OUTPUT_DATA_BEGIN() throws IOException,SQLException,Exception {
        if (WS_PAGE_INFO.ROW_FLG == 'Y') {
            WS_PAGE_INFO.BAL_CNT = WS_PAGE_INFO.TOTAL_NUM % WS_PAGE_INFO.PAGE_ROW;
            WS_PAGE_INFO.TOTAL_PAGE = (short) ((WS_PAGE_INFO.TOTAL_NUM - WS_PAGE_INFO.BAL_CNT) / WS_PAGE_INFO.PAGE_ROW);
            if (WS_PAGE_INFO.BAL_CNT != 0) {
                WS_PAGE_INFO.TOTAL_PAGE += 1;
            }
            if (WS_PAGE_INFO.TOTAL_PAGE == WS_PAGE_INFO.CURR_PAGE) {
                WS_PAGE_INFO.LAST_PAGE = 'Y';
                if (WS_PAGE_INFO.BAL_CNT != 0) {
                    WS_PAGE_INFO.PAGE_ROW = (short) WS_PAGE_INFO.BAL_CNT;
                }
            }
            WS_OUT_RECODE.WS_OUT_HEAD.O_CURR_PAGE_ROW = WS_PAGE_INFO.PAGE_IDX;
        } else {
            WS_PAGE_INFO.TOTAL_PAGE = 1;
            WS_PAGE_INFO.TOTAL_NUM = 0;
            WS_PAGE_INFO.LAST_PAGE = 'Y';
            WS_PAGE_INFO.PAGE_ROW = 0;
            WS_PAGE_INFO.CURR_PAGE = 1;
            WS_OUT_RECODE.WS_OUT_HEAD.O_CURR_PAGE_ROW = 0;
        }
        WS_OUT_RECODE.WS_OUT_HEAD.O_TOTAL_PAGE = WS_PAGE_INFO.TOTAL_PAGE;
        WS_OUT_RECODE.WS_OUT_HEAD.O_TOTAL_NUM = WS_PAGE_INFO.TOTAL_NUM;
        WS_OUT_RECODE.WS_OUT_HEAD.O_CURR_PAGE = WS_PAGE_INFO.CURR_PAGE;
        WS_OUT_RECODE.WS_OUT_HEAD.O_LAST_PAGE = WS_PAGE_INFO.LAST_PAGE;
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_VARIABLES.WS_TEMP_VARIABLE.MSGID);
        CEP.ERR(SCCGWA, WS_VARIABLES.WS_TEMP_VARIABLE.MSGID);
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
