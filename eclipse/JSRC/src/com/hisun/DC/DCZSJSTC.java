package com.hisun.DC;

import com.hisun.SC.*;
import com.hisun.DD.*;
import com.hisun.BP.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class DCZSJSTC {
    DCZSJSTC_WS_VAR_FLAG WS_VAR_FLAG;
    int JIBS_tmp_int;
    DBParm DCTIAACR_RD;
    DBParm DCTBINPM_RD;
    DBParm DCTACLNK_RD;
    brParm DCTIAACR_BR = new brParm();
    DBParm DCTCDDAT_RD;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    int MAX_COL = 99;
    String OUT_FMT = "DC630";
    short PAGE_ROW = 25;
    DCZSJSTC_WS_VARIABLES WS_VARIABLES = new DCZSJSTC_WS_VARIABLES();
    DCZSJSTC_WS_OUT_RECODE WS_OUT_RECODE = new DCZSJSTC_WS_OUT_RECODE();
    DCZSJSTC_WS_CONDITION_FLAG WS_CONDITION_FLAG = new DCZSJSTC_WS_CONDITION_FLAG();
    DCCMSG_ERROR_MSG ERROR_MSG = new DCCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    DCRBINPM DCRBINPM = new DCRBINPM();
    DCRACLNK DCRACLNK = new DCRACLNK();
    DCCPQPRD DCCPQPRD = new DCCPQPRD();
    DCRCDDAT DCRCDDAT = new DCRCDDAT();
    DDCSCINM DDCSCINM = new DDCSCINM();
    BPCPQPRD BPCPQPRD = new BPCPQPRD();
    BPCPQPRD_WS_DB_VARS WS_DB_VARS = new BPCPQPRD_WS_DB_VARS();
    DCRIAACR DCRIAACR = new DCRIAACR();
    DCRIAMST DCRIAMST = new DCRIAMST();
    SCCGWA SCCGWA;
    DCCSJSTC DCCSJSTC;
    SCCGSCA_SC_AREA SC_AREA;
    SCCGBPA_BP_AREA BP_AREA;
    public void MP(SCCGWA SCCGWA, DCCSJSTC DCCSJSTC) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DCCSJSTC = DCCSJSTC;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DCZSJSTC return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, WS_VARIABLES);
        IBS.init(SCCGWA, WS_CONDITION_FLAG);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        B020_GET_DATA();
        if (pgmRtn) return;
        B030_OUTPUT_PROC();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DCCSJSTC.CARD_NO);
        CEP.TRC(SCCGWA, DCCSJSTC.PAGE_NUM);
        CEP.TRC(SCCGWA, DCCSJSTC.PAGE_ROW);
        if (DCCSJSTC.CARD_NO.trim().length() == 0) {
            WS_VARIABLES.ERR_MSG = ERROR_MSG.DC_CARD_NO_MUST_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCCSJSTC.PAGE_NUM == 0) {
            WS_VARIABLES.CURR_PAGE = 1;
        } else {
            WS_VARIABLES.CURR_PAGE = DCCSJSTC.PAGE_NUM;
        }
        WS_OUT_RECODE.WS_OUT_HEAD.O_CURR_PAGE = WS_VARIABLES.CURR_PAGE;
        WS_VARIABLES.LAST_PAGE = 'N';
        if (DCCSJSTC.PAGE_ROW == 0) {
            WS_VARIABLES.PAGE_ROW = PAGE_ROW;
            WS_VAR_FLAG = new DCZSJSTC_WS_VAR_FLAG();
            WS_OUT_RECODE.WS_VAR_FLAG.add(WS_VAR_FLAG);
            CEP.TRC(SCCGWA, WS_VARIABLES.PAGE_ROW);
            CEP.TRC(SCCGWA, PAGE_ROW);
        } else {
            if (DCCSJSTC.PAGE_ROW > PAGE_ROW) {
                WS_VARIABLES.ERR_MSG = ERROR_MSG.DC_ERR_PAGE_ROW;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            } else {
                WS_VARIABLES.PAGE_ROW = DCCSJSTC.PAGE_ROW;
                WS_VAR_FLAG = new DCZSJSTC_WS_VAR_FLAG();
                WS_OUT_RECODE.WS_VAR_FLAG.add(WS_VAR_FLAG);
            }
            CEP.TRC(SCCGWA, WS_VARIABLES.PAGE_ROW);
        }
        WS_VARIABLES.NEXT_START_NUM = ( ( WS_VARIABLES.CURR_PAGE - 1 ) * WS_VARIABLES.PAGE_ROW ) + 1;
        WS_DB_VARS.START_NUM = WS_VARIABLES.NEXT_START_NUM;
        CEP.TRC(SCCGWA, WS_VARIABLES.CURR_PAGE);
        CEP.TRC(SCCGWA, WS_VARIABLES.PAGE_ROW);
        CEP.TRC(SCCGWA, WS_VARIABLES.TOTAL_NUM);
        CEP.TRC(SCCGWA, WS_VARIABLES.IDX);
        CEP.TRC(SCCGWA, WS_VARIABLES.NEXT_START_NUM);
    }
    public void B020_GET_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCRBINPM);
        IBS.init(SCCGWA, DCRIAACR);
        IBS.init(SCCGWA, DCRACLNK);
        IBS.init(SCCGWA, DCRCDDAT);
        DCRCDDAT.KEY.CARD_NO = DCCSJSTC.CARD_NO;
        T000_READ_DCTCDDAT();
        if (pgmRtn) return;
        if (DCRCDDAT.CARD_STS == 'C') {
            WS_VARIABLES.ERR_MSG = ERROR_MSG.DC_CARD_BE_CLOSED_W;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, BPCPQPRD);
        BPCPQPRD.PRDT_CODE = DCRCDDAT.PROD_CD;
        S000_CALL_BPZPQPRD();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCPQPRD.PARM_CODE);
        IBS.init(SCCGWA, DCCPQPRD);
        if (BPCPQPRD.PARM_CODE.trim().length() > 0) {
            DCCPQPRD.VAL.KEY.PROD_CD = BPCPQPRD.PARM_CODE;
        } else {
            DCCPQPRD.VAL.KEY.PROD_CD = DCRCDDAT.PROD_CD;
        }
        S000_CALL_DCZPQPRD();
        if (pgmRtn) return;
        if (DCCPQPRD.VAL.DATA.ADSC_TYPE != 'C ') {
            WS_VARIABLES.ERR_MSG = ERROR_MSG.DC_MUST_COMPANY_CARD;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCCSJSTC.CARD_NO == null) DCCSJSTC.CARD_NO = "";
        JIBS_tmp_int = DCCSJSTC.CARD_NO.length();
        for (int i=0;i<19-JIBS_tmp_int;i++) DCCSJSTC.CARD_NO += " ";
        if (DCCSJSTC.CARD_NO.substring(0, 4).equalsIgnoreCase("9111")) {
            CEP.TRC(SCCGWA, "149111");
            if (DCCSJSTC.CARD_NO == null) DCCSJSTC.CARD_NO = "";
            JIBS_tmp_int = DCCSJSTC.CARD_NO.length();
            for (int i=0;i<19-JIBS_tmp_int;i++) DCCSJSTC.CARD_NO += " ";
            DCRBINPM.KEY.BIN = DCCSJSTC.CARD_NO.substring(0, 4);
        } else {
            CEP.TRC(SCCGWA, "666666");
            if (DCCSJSTC.CARD_NO == null) DCCSJSTC.CARD_NO = "";
            JIBS_tmp_int = DCCSJSTC.CARD_NO.length();
            for (int i=0;i<19-JIBS_tmp_int;i++) DCCSJSTC.CARD_NO += " ";
            DCRBINPM.KEY.BIN = DCCSJSTC.CARD_NO.substring(0, 6);
        }
        T00_READ_DCTBINPM();
        if (pgmRtn) return;
        if (WS_CONDITION_FLAG.BIN_FLAG == 'Y') {
            DCRACLNK.KEY.CARD_NO = DCCSJSTC.CARD_NO;
            T00_READ_DCTACLNK();
            if (pgmRtn) return;
            if (WS_CONDITION_FLAG.LNK_FLAG == 'Y' 
                && DCRACLNK.CARD_AC_STATUS == '1') {
                DCRIAACR.KEY.VIA_AC = DCRACLNK.VIA_AC;
                B030_PROC_BROWSE_PROC();
                if (pgmRtn) return;
            } else {
                WS_OUT_RECODE.WS_OUT_HEAD.O_CURR_PAGE = 0;
                WS_OUT_RECODE.WS_OUT_HEAD.O_TOTAL_PAGE = 0;
                WS_OUT_RECODE.WS_OUT_HEAD.O_TOTAL_NUM = 0;
                WS_OUT_RECODE.WS_OUT_HEAD.O_LAST_PAGE = 'Y';
                WS_OUT_RECODE.WS_OUT_HEAD.O_CURR_PAGE_ROW = 0;
            }
        }
        CEP.TRC(SCCGWA, WS_OUT_RECODE.WS_OUT_HEAD.O_TOTAL_PAGE);
        CEP.TRC(SCCGWA, WS_OUT_RECODE.WS_OUT_HEAD.O_TOTAL_NUM);
        CEP.TRC(SCCGWA, WS_OUT_RECODE.WS_OUT_HEAD.O_CURR_PAGE);
        CEP.TRC(SCCGWA, WS_OUT_RECODE.WS_OUT_HEAD.O_LAST_PAGE);
        CEP.TRC(SCCGWA, WS_OUT_RECODE.WS_OUT_HEAD.O_CURR_PAGE_ROW);
    }
    public void B030_PROC_BROWSE_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DCRIAACR.KEY.VIA_AC);
        T000_STARTBR_DCTIAACR();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "0000000");
        T000_READNEXT_DCTIAACR();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "11111111");
        if (WS_CONDITION_FLAG.TBL_FLAG != 'N') {
            CEP.TRC(SCCGWA, "XXXX");
            WS_VARIABLES.IDX = 0;
            WS_VARIABLES.TOTAL_NUM = 0;
            while (WS_VARIABLES.IDX != WS_VARIABLES.PAGE_ROW 
                && WS_CONDITION_FLAG.TBL_FLAG != 'N') {
                B000_WRITE_DATA_PROC();
                if (pgmRtn) return;
                WS_VARIABLES.NEXT_START_NUM += 1;
                WS_DB_VARS.START_NUM = WS_VARIABLES.NEXT_START_NUM;
                T000_READNEXT_DCTIAACR();
                if (pgmRtn) return;
            }
            if (WS_CONDITION_FLAG.TBL_FLAG == 'N') {
                CEP.TRC(SCCGWA, "IF IF");
                WS_VARIABLES.TOTAL_PAGE = WS_VARIABLES.CURR_PAGE;
                WS_VARIABLES.BAL_CNT = WS_VARIABLES.IDX;
                WS_VARIABLES.TOTAL_NUM = ( WS_VARIABLES.CURR_PAGE - 1 ) * WS_VARIABLES.PAGE_ROW + WS_VARIABLES.BAL_CNT;
                WS_VARIABLES.LAST_PAGE = 'Y';
                WS_VARIABLES.PAGE_ROW = WS_VARIABLES.BAL_CNT;
                WS_VAR_FLAG = new DCZSJSTC_WS_VAR_FLAG();
                WS_OUT_RECODE.WS_VAR_FLAG.add(WS_VAR_FLAG);
            } else {
                CEP.TRC(SCCGWA, "IF ELSE");
                R000_GROUP_ALL();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, WS_VARIABLES.TOTAL_NUM);
                CEP.TRC(SCCGWA, WS_VARIABLES.PAGE_ROW);
                WS_VARIABLES.BAL_CNT = WS_VARIABLES.TOTAL_NUM % WS_VARIABLES.PAGE_ROW;
                WS_VARIABLES.TOTAL_PAGE = (int) ((WS_VARIABLES.TOTAL_NUM - WS_VARIABLES.BAL_CNT) / WS_VARIABLES.PAGE_ROW);
                if (WS_VARIABLES.BAL_CNT != 0) {
                    WS_VARIABLES.TOTAL_PAGE += 1;
                }
            }
        } else {
            WS_VARIABLES.TOTAL_PAGE = 1;
            WS_VARIABLES.TOTAL_NUM = 0;
            WS_VARIABLES.LAST_PAGE = 'Y';
            WS_VARIABLES.PAGE_ROW = 0;
            WS_VAR_FLAG = new DCZSJSTC_WS_VAR_FLAG();
            WS_OUT_RECODE.WS_VAR_FLAG.add(WS_VAR_FLAG);
        }
        T000_ENDBR_DCTIAACR();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, WS_VARIABLES.TOTAL_PAGE);
        CEP.TRC(SCCGWA, WS_VARIABLES.TOTAL_NUM);
        CEP.TRC(SCCGWA, WS_VARIABLES.LAST_PAGE);
        CEP.TRC(SCCGWA, WS_VARIABLES.PAGE_ROW);
        WS_OUT_RECODE.WS_OUT_HEAD.O_TOTAL_PAGE = WS_VARIABLES.TOTAL_PAGE;
        WS_OUT_RECODE.WS_OUT_HEAD.O_TOTAL_NUM = WS_VARIABLES.TOTAL_NUM;
        WS_OUT_RECODE.WS_OUT_HEAD.O_LAST_PAGE = WS_VARIABLES.LAST_PAGE;
        WS_OUT_RECODE.WS_OUT_HEAD.O_CURR_PAGE_ROW = WS_VARIABLES.PAGE_ROW;
    }
    public void B000_WRITE_DATA_PROC() throws IOException,SQLException,Exception {
        WS_VARIABLES.IDX += 1;
        IBS.init(SCCGWA, WS_VAR_FLAG);
        IBS.init(SCCGWA, DDCSCINM);
        DDCSCINM.INPUT_DATA.AC_NO = DCRIAACR.SUB_AC;
        DDCSCINM.INPUT_DATA.FUNC = '2';
        S000_CALL_DDZSCINM();
        if (pgmRtn) return;
        WS_VAR_FLAG.AC_NO = DCRIAACR.SUB_AC;
        if (DDCSCINM.OUTPUT_DATA.AC_CNM.trim().length() > 0) {
            WS_VAR_FLAG.AC_NAME = DDCSCINM.OUTPUT_DATA.AC_CNM;
        } else {
            WS_VAR_FLAG.AC_NAME = DDCSCINM.OUTPUT_DATA.AC_ENM;
        }
        WS_VAR_FLAG.DEFAULT_FLG = DCRIAACR.DEFAULT_FLG;
    }
    public void B030_OUTPUT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = OUT_FMT;
        SCCFMT.DATA_PTR = WS_OUT_RECODE;
        SCCFMT.DATA_LEN = 7172;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void R000_GROUP_ALL() throws IOException,SQLException,Exception {
        T000_GROUP_REC_PROC();
        if (pgmRtn) return;
        WS_VARIABLES.TOTAL_NUM = WS_DB_VARS.CNT;
        CEP.TRC(SCCGWA, WS_VARIABLES.TOTAL_NUM);
    }
    public void T000_GROUP_REC_PROC() throws IOException,SQLException,Exception {
        DCTIAACR_RD = new DBParm();
        DCTIAACR_RD.TableName = "DCTIAACR";
        DCTIAACR_RD.where = "VIA_AC = :DCRIAACR.KEY.VIA_AC";
        DCTIAACR_RD.set = "WS-CNT=COUNT(*)";
        IBS.GROUP(SCCGWA, DCRIAACR, this, DCTIAACR_RD);
    }
    public void S000_CALL_DDZSCINM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-SVR-CI-NAME", DDCSCINM, true);
    }
    public void T00_READ_DCTBINPM() throws IOException,SQLException,Exception {
        DCTBINPM_RD = new DBParm();
        DCTBINPM_RD.TableName = "DCTBINPM";
        IBS.READ(SCCGWA, DCRBINPM, DCTBINPM_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_CONDITION_FLAG.BIN_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_CONDITION_FLAG.BIN_FLAG = 'N';
        } else {
        }
    }
    public void T00_READ_DCTACLNK() throws IOException,SQLException,Exception {
        DCTACLNK_RD = new DBParm();
        DCTACLNK_RD.TableName = "DCTACLNK";
        IBS.READ(SCCGWA, DCRACLNK, DCTACLNK_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_CONDITION_FLAG.LNK_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_CONDITION_FLAG.LNK_FLAG = 'N';
        } else {
        }
    }
    public void T000_STARTBR_DCTIAACR() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DCRIAACR.KEY.VIA_AC);
        DCRIAACR.ACCR_FLG = '1';
        DCTIAACR_BR.rp = new DBParm();
        DCTIAACR_BR.rp.TableName = "DCTIAACR";
        DCTIAACR_BR.rp.where = "VIA_AC = :DCRIAACR.KEY.VIA_AC "
            + "AND ACCR_FLG = :DCRIAACR.ACCR_FLG";
        DCTIAACR_BR.rp.order = "SEQ";
        IBS.STARTBR(SCCGWA, DCRIAACR, this, DCTIAACR_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_CONDITION_FLAG.TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_CONDITION_FLAG.TBL_FLAG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DCTIAACR";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "STARTBR";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READNEXT_DCTIAACR() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, DCRIAACR, this, DCTIAACR_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_CONDITION_FLAG.TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_CONDITION_FLAG.TBL_FLAG = 'N';
        } else {
        }
    }
    public void T000_ENDBR_DCTIAACR() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, DCTIAACR_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DCTIAACR";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READNEXT";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_DCTCDDAT() throws IOException,SQLException,Exception {
        DCTCDDAT_RD = new DBParm();
        DCTCDDAT_RD.TableName = "DCTCDDAT";
        IBS.READ(SCCGWA, DCRCDDAT, DCTCDDAT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_VARIABLES.ERR_MSG = ERROR_MSG.DC_CARD_NOT_EXIST;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPQPRD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-QUERY-PRDT-INFO", BPCPQPRD);
        if (BPCPQPRD.RC.RC_CODE != 0) {
            WS_VARIABLES.ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQPRD.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_DCZPQPRD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-P-QUERY-PROD", DCCPQPRD);
        if (DCCPQPRD.RC.RC_CODE != 0) {
            WS_VARIABLES.ERR_MSG = IBS.CLS2CPY(SCCGWA, DCCPQPRD.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_VARIABLES.ERR_MSG);
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
