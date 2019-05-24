package com.hisun.DC;

import com.hisun.BP.*;
import com.hisun.SC.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class DCZSCLCS {
    String JIBS_tmp_str[] = new String[10];
    brParm DCTDCCLS_BR = new brParm();
    DBParm DCTDCCLS_RD;
    boolean pgmRtn = false;
    String K_OUTPUT_FMT_9 = "DC054";
    int K_MAX_COL = 20;
    int K_MAX_ROW = 20;
    int K_COL_CNT = 18;
    String K_HIS_REMARK = "THE CARD CLASS PARAMETER MAINTAIN";
    String K_HIS_COPYBOOK = "DCRDCCLS";
    String K_TBL_DCCLS = "DCTDCCLS";
    String K_PRDT_INF_MAINT = "DC-S-MAINT-CLS-INFO";
    String WS_ERR_MSG = " ";
    int WS_CNT = 0;
    DCZSCLCS_WS_OUTPUT_VAL WS_OUTPUT_VAL = new DCZSCLCS_WS_OUTPUT_VAL();
    char WS_TBL_FLAG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    DCRDCCLS DCRDCCLS = new DCRDCCLS();
    DCRDCCLS DCRDCCLO = new DCRDCCLS();
    BPRPARM BPRPARM = new BPRPARM();
    BPCPRMM BPCPRMM = new BPCPRMM();
    BPCPRMR BPCPRMR = new BPCPRMR();
    BPCRMBPM BPCRMBPM = new BPCRMBPM();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    BPCSMPRD BPCSMPRD = new BPCSMPRD();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    DCCSCLCS DCCSCLCS;
    public void MP(SCCGWA SCCGWA, DCCSCLCS DCCSCLCS) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DCCSCLCS = DCCSCLCS;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DCZSCLCS return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, DCRDCCLS);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DCCSCLCS);
        CEP.TRC(SCCGWA, DCCSCLCS.FUNC);
        if (DCCSCLCS.FUNC == 'A') {
            B001_CHECK_INPUT();
            if (pgmRtn) return;
            B020_CREATE_PROCESS();
            if (pgmRtn) return;
            B080_HISTORY_PROCESS();
            if (pgmRtn) return;
            B090_DATA_OUTPUT();
            if (pgmRtn) return;
        } else if (DCCSCLCS.FUNC == 'U') {
            B001_CHECK_INPUT();
            if (pgmRtn) return;
            B030_MODIFY_PROCESS();
            if (pgmRtn) return;
            B080_HISTORY_PROCESS();
            if (pgmRtn) return;
            B090_DATA_OUTPUT();
            if (pgmRtn) return;
        } else if (DCCSCLCS.FUNC == 'D') {
            B001_CHECK_INPUT();
            if (pgmRtn) return;
            B040_DELETE_PROCESS();
            if (pgmRtn) return;
            B080_HISTORY_PROCESS();
            if (pgmRtn) return;
            B090_DATA_OUTPUT();
            if (pgmRtn) return;
        } else if (DCCSCLCS.FUNC == 'B') {
            B050_BROWSER_PROCESS();
            if (pgmRtn) return;
        } else {
            CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.FUNC_FALSE, 1);
        }
    }
    public void B001_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DCCSCLCS.VAL.KEY.CARD_PD);
        CEP.TRC(SCCGWA, DCCSCLCS.VAL.KEY.CARD_CLS);
        CEP.TRC(SCCGWA, DCCSCLCS.VAL.BV_CD_NO);
        if (DCCSCLCS.VAL.KEY.CARD_PD.trim().length() == 0) {
            CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_PROD_CD_M_INPUT);
        }
        if (DCCSCLCS.VAL.KEY.CARD_CLS.trim().length() == 0) {
            CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_CARD_CLS_CD_MISSING);
        }
        if (DCCSCLCS.VAL.BV_CD_NO.trim().length() == 0) {
            CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_BV_CD_NO_MISSING);
        }
    }
    public void B010_QUERY_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCRDCCLS);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, DCCSCLCS.VAL.KEY);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], DCRDCCLS.KEY);
        T000_READ_DCTDCCLS();
        if (pgmRtn) return;
        if (WS_TBL_FLAG == 'N') {
            CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.NOT_FOUND);
        }
    }
    public void B020_CREATE_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCRDCCLS);
        CEP.TRC(SCCGWA, DCCSCLCS.VAL.KEY.CARD_PD);
        DCRDCCLS.KEY.CARD_PROD_CD = DCCSCLCS.VAL.KEY.CARD_PD;
        CEP.TRC(SCCGWA, DCCSCLCS.VAL.KEY.CARD_CLS);
        DCRDCCLS.KEY.CARD_CLS_CD = DCCSCLCS.VAL.KEY.CARD_CLS;
        CEP.TRC(SCCGWA, DCCSCLCS.VAL.CLS_NM);
        DCRDCCLS.CARD_CLS_NM = DCCSCLCS.VAL.CLS_NM;
        CEP.TRC(SCCGWA, DCCSCLCS.VAL.BV_CD_NO);
        DCRDCCLS.BV_CD_NO = DCCSCLCS.VAL.BV_CD_NO;
        DCRDCCLS.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DCRDCCLS.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        DCRDCCLS.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DCRDCCLS.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        T000_WRITE_DCTDCCLS();
        if (pgmRtn) return;
        if (WS_TBL_FLAG == 'D') {
            CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DUPKEY);
        }
    }
    public void B030_MODIFY_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCRDCCLS);
        DCRDCCLS.KEY.CARD_PROD_CD = DCCSCLCS.VAL.KEY.CARD_PD;
        DCRDCCLS.KEY.CARD_CLS_CD = DCCSCLCS.VAL.KEY.CARD_CLS;
        T000_READ_DCTDCCLS_UPD();
        if (pgmRtn) return;
        if (WS_TBL_FLAG == 'N') {
            CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.NOT_FOUND);
        }
        IBS.CLONE(SCCGWA, DCRDCCLS, DCRDCCLO);
        DCRDCCLS.KEY.CARD_PROD_CD = DCCSCLCS.VAL.KEY.CARD_PD;
        DCRDCCLS.KEY.CARD_CLS_CD = DCCSCLCS.VAL.KEY.CARD_CLS;
        DCRDCCLS.CARD_CLS_NM = DCCSCLCS.VAL.CLS_NM;
        DCRDCCLS.BV_CD_NO = DCCSCLCS.VAL.BV_CD_NO;
        DCRDCCLS.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DCRDCCLS.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        T000_REWRITE_DCTDCCLS();
        if (pgmRtn) return;
        if (WS_TBL_FLAG == 'D') {
            CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DUPKEY);
        }
    }
    public void B040_DELETE_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCRDCCLS);
        DCRDCCLS.KEY.CARD_PROD_CD = DCCSCLCS.VAL.KEY.CARD_PD;
        DCRDCCLS.KEY.CARD_CLS_CD = DCCSCLCS.VAL.KEY.CARD_CLS;
        T000_READ_DCTDCCLS_UPD();
        if (pgmRtn) return;
        if (WS_TBL_FLAG == 'N') {
            CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.NOT_FOUND);
        }
        IBS.CLONE(SCCGWA, DCRDCCLS, DCRDCCLO);
        T000_DELETE_DCTDCCLS();
        if (pgmRtn) return;
    }
    public void B050_BROWSER_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCRDCCLS);
        if (DCCSCLCS.VAL.KEY.CARD_PD.trim().length() == 0) {
            DCRDCCLS.KEY.CARD_PROD_CD = "%%%%%%%%%%";
        } else {
            DCRDCCLS.KEY.CARD_PROD_CD = DCCSCLCS.VAL.KEY.CARD_PD;
        }
        if (DCCSCLCS.VAL.KEY.CARD_CLS.trim().length() == 0) {
            DCRDCCLS.KEY.CARD_CLS_CD = "%%%%%%%%%%";
        } else {
            DCRDCCLS.KEY.CARD_CLS_CD = DCCSCLCS.VAL.KEY.CARD_CLS;
        }
        T000_STARTBR_DCTDCCLS();
        if (pgmRtn) return;
        T000_READNEXT_DCTDCCLS();
        if (pgmRtn) return;
        B050_01_01_OUT_TITLE();
        if (pgmRtn) return;
        WS_CNT = 0;
        while (WS_TBL_FLAG != 'N' 
            && SCCMPAG.FUNC != 'E') {
            B050_01_02_OUT_BRW_DATA();
            if (pgmRtn) return;
            WS_CNT += 1;
            T000_READNEXT_DCTDCCLS();
            if (pgmRtn) return;
        }
        T000_ENDBR_DCTDCCLS();
        if (pgmRtn) return;
    }
    public void B050_01_01_OUT_TITLE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.TITL = " ";
        SCCMPAG.SUBT_ROW_CNT = 0;
        SCCMPAG.MAX_COL_NO = (short) K_MAX_COL;
        SCCMPAG.SCR_ROW_CNT = (short) K_MAX_ROW;
        SCCMPAG.SCR_COL_CNT = (short) K_COL_CNT;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void B050_01_02_OUT_BRW_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DCRDCCLS.KEY.CARD_PROD_CD);
        WS_OUTPUT_VAL.WS_CARD_PROD_CD = DCRDCCLS.KEY.CARD_PROD_CD;
        CEP.TRC(SCCGWA, DCRDCCLS.KEY.CARD_CLS_CD);
        WS_OUTPUT_VAL.WS_CARD_CLS_INFO.WS_CARD_CLS_CD = DCRDCCLS.KEY.CARD_CLS_CD;
        CEP.TRC(SCCGWA, DCRDCCLS.CARD_CLS_NM);
        WS_OUTPUT_VAL.WS_CARD_CLS_INFO.WS_CARD_CLS_CD_NM = DCRDCCLS.CARD_CLS_NM;
        CEP.TRC(SCCGWA, DCRDCCLS.BV_CD_NO);
        WS_OUTPUT_VAL.WS_BV_CD_NO = DCRDCCLS.BV_CD_NO;
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, WS_OUTPUT_VAL);
        SCCMPAG.DATA_LEN = 87;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void B080_HISTORY_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_RMK = K_HIS_REMARK;
        BPCPNHIS.INFO.FMT_ID = K_HIS_COPYBOOK;
        BPCPNHIS.INFO.FMT_ID_LEN = 147;
        if (DCCSCLCS.FUNC == 'A') {
            BPCPNHIS.INFO.TX_TYP = 'A';
            BPCPNHIS.INFO.DATA_FLG = 'Y';
            BPCPNHIS.INFO.NEW_DAT_PT = DCRDCCLS;
        }
        if (DCCSCLCS.FUNC == 'D') {
            BPCPNHIS.INFO.TX_TYP = 'D';
            BPCPNHIS.INFO.DATA_FLG = 'Y';
            BPCPNHIS.INFO.OLD_DAT_PT = DCRDCCLO;
        }
        if (DCCSCLCS.FUNC == 'U') {
            BPCPNHIS.INFO.TX_TYP = 'M';
            BPCPNHIS.INFO.OLD_DAT_PT = DCRDCCLO;
            BPCPNHIS.INFO.NEW_DAT_PT = DCRDCCLS;
        }
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void B090_DATA_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCFMT);
        WS_OUTPUT_VAL.WS_CARD_PROD_CD = DCRDCCLS.KEY.CARD_PROD_CD;
        WS_OUTPUT_VAL.WS_CARD_CLS_INFO.WS_CARD_CLS_CD = DCRDCCLS.KEY.CARD_CLS_CD;
        WS_OUTPUT_VAL.WS_CARD_CLS_INFO.WS_CARD_CLS_CD_NM = DCRDCCLS.CARD_CLS_NM;
        WS_OUTPUT_VAL.WS_BV_CD_NO = DCRDCCLS.BV_CD_NO;
        SCCFMT.FMTID = K_OUTPUT_FMT_9;
        SCCFMT.DATA_PTR = WS_OUTPUT_VAL;
        SCCFMT.DATA_LEN = 87;
        CEP.TRC(SCCGWA, WS_OUTPUT_VAL);
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-REC-NHIS         ", BPCPNHIS);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPNHIS.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_DCTDCCLS() throws IOException,SQLException,Exception {
        DCTDCCLS_BR.rp = new DBParm();
        DCTDCCLS_BR.rp.TableName = "DCTDCCLS";
        DCTDCCLS_BR.rp.where = "CARD_PROD_CD LIKE :DCRDCCLS.KEY.CARD_PROD_CD "
            + "AND CARD_CLS_CD LIKE :DCRDCCLS.KEY.CARD_CLS_CD";
        DCTDCCLS_BR.rp.order = "CARD_PROD_CD DESC";
        IBS.STARTBR(SCCGWA, DCRDCCLS, this, DCTDCCLS_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TBL_FLAG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = K_TBL_DCCLS;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "STARTBR";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READNEXT_DCTDCCLS() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, DCRDCCLS, this, DCTDCCLS_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TBL_FLAG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = K_TBL_DCCLS;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READNEXT";
            B_DB_EXCP();
            if (pgmRtn) return;
    }
    public void T000_ENDBR_DCTDCCLS() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, DCTDCCLS_BR);
    }
    public void T000_READ_DCTDCCLS() throws IOException,SQLException,Exception {
        DCTDCCLS_RD = new DBParm();
        DCTDCCLS_RD.TableName = "DCTDCCLS";
        DCTDCCLS_RD.col = "CARD_PROD_CD, CARD_CLS_CD, CARD_CLS_NM, BV_CD_NO, CRT_DATE, CRT_TLR, UPDTBL_DATE, UPDTBL_TLR";
        IBS.READ(SCCGWA, DCRDCCLS, DCTDCCLS_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TBL_FLAG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = K_TBL_DCCLS;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_WRITE_DCTDCCLS() throws IOException,SQLException,Exception {
        DCTDCCLS_RD = new DBParm();
        DCTDCCLS_RD.TableName = "DCTDCCLS";
        DCTDCCLS_RD.errhdl = true;
        IBS.WRITE(SCCGWA, DCRDCCLS, DCTDCCLS_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            WS_TBL_FLAG = 'D';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = K_TBL_DCCLS;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "WRITE";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_DCTDCCLS_UPD() throws IOException,SQLException,Exception {
        DCTDCCLS_RD = new DBParm();
        DCTDCCLS_RD.TableName = "DCTDCCLS";
        DCTDCCLS_RD.upd = true;
        IBS.READ(SCCGWA, DCRDCCLS, DCTDCCLS_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TBL_FLAG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = K_TBL_DCCLS;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_REWRITE_DCTDCCLS() throws IOException,SQLException,Exception {
        DCTDCCLS_RD = new DBParm();
        DCTDCCLS_RD.TableName = "DCTDCCLS";
        IBS.REWRITE(SCCGWA, DCRDCCLS, DCTDCCLS_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            WS_TBL_FLAG = 'D';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = K_TBL_DCCLS;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "REWRITE";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_DELETE_DCTDCCLS() throws IOException,SQLException,Exception {
        DCTDCCLS_RD = new DBParm();
        DCTDCCLS_RD.TableName = "DCTDCCLS";
        IBS.DELETE(SCCGWA, DCRDCCLS, DCTDCCLS_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TBL_FLAG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = K_TBL_DCCLS;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "DELETE";
            B_DB_EXCP();
            if (pgmRtn) return;
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
