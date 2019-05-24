package com.hisun.DC;

import com.hisun.SC.*;
import com.hisun.BP.*;
import com.hisun.CI.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class DCZSCINQ {
    int JIBS_tmp_int;
    DBParm DCTCDDAT_RD;
    brParm DCTCDDAT_BR = new brParm();
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String CPN_BPZSMPRD = "BP-S-MAINT-PRDT-INFO";
    String CPN_CIZSSCH = "CI-SEARCH-CI";
    int K_MAX_ROW = 99;
    int K_MAX_COL = 99;
    String WS_ERR_MSG = " ";
    int WS_I = 0;
    long WS_SEQNO = 0;
    long WS_BATCHNO = 0;
    DCZSCINQ_WS_BRW_OUTPUT WS_BRW_OUTPUT = new DCZSCINQ_WS_BRW_OUTPUT();
    String WS_OWN_CIFNO = " ";
    String WS_HLDR_CIFNO = " ";
    char WS_RETURN_INFO = ' ';
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCMSG SCCMSG = new SCCMSG();
    DCRCDDAT DCRCDDAT = new DCRCDDAT();
    BPCSMPRD BPCSMPRD = new BPCSMPRD();
    BPCSGSEQ BPCSGSEQ = new BPCSGSEQ();
    CICCUST CICCUST = new CICCUST();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    DCCSCINQ DCCSCINQ;
    public void MP(SCCGWA SCCGWA, DCCSCINQ DCCSCINQ) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DCCSCINQ = DCCSCINQ;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DCZSCINQ return!");
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
        B020_GET_CIF_NO();
        if (pgmRtn) return;
        B030_GET_CARD_INFO();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DCCSCINQ.CARD_NO0);
        CEP.TRC(SCCGWA, DCCSCINQ.HLDR_ID_TYPE);
        CEP.TRC(SCCGWA, DCCSCINQ.HLDR_ID_NO);
        CEP.TRC(SCCGWA, DCCSCINQ.HLDR_NAME);
        if (DCCSCINQ.CARD_NO0.trim().length() == 0 
            && DCCSCINQ.HLDR_ID_TYPE.trim().length() == 0 
            && DCCSCINQ.HLDR_ID_TYPE.equalsIgnoreCase(DCCSCINQ.HLDR_ID_NO)) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_MISSING_INPUT_FIELD;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if ((DCCSCINQ.HLDR_ID_TYPE.trim().length() > 0 
            && DCCSCINQ.HLDR_ID_NO.trim().length() == 0) 
            || (DCCSCINQ.HLDR_ID_TYPE.trim().length() == 0 
            && DCCSCINQ.HLDR_ID_NO.trim().length() > 0)) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_IDTYPE_IDNO_SAMETIME;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B020_GET_CIF_NO() throws IOException,SQLException,Exception {
        if (DCCSCINQ.HLDR_ID_TYPE.trim().length() > 0 
            && DCCSCINQ.HLDR_ID_NO.trim().length() > 0) {
            IBS.init(SCCGWA, CICCUST);
            CICCUST.DATA.ID_TYPE = DCCSCINQ.HLDR_ID_TYPE;
            CICCUST.DATA.ID_NO = DCCSCINQ.HLDR_ID_NO;
            CICCUST.DATA.CI_NM = DCCSCINQ.HLDR_NAME;
            CICCUST.FUNC = 'B';
            S000_CALL_CIZCUST();
            if (pgmRtn) return;
            WS_HLDR_CIFNO = CICCUST.O_DATA.O_CI_NO;
        }
    }
    public void B030_GET_CARD_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCRCDDAT);
        if (DCCSCINQ.CARD_NO0.trim().length() == 0) {
            CEP.TRC(SCCGWA, WS_HLDR_CIFNO);
            DCRCDDAT.CARD_HLDR_CINO = WS_HLDR_CIFNO;
            DCRCDDAT.CARD_STS = 'N';
            T000_STARTBR_DCTCDDAT();
            if (pgmRtn) return;
            T000_READNEXT_DCTCDDAT();
            if (pgmRtn) return;
            B031_OUTPUT_TITLE();
            if (pgmRtn) return;
            while (SCCGWA.COMM_AREA.DBIO_FLG != '1' 
                && SCCMPAG.FUNC != 'E') {
                WS_I = WS_I + 1;
                B032_OUTPUT_DETAIL();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, DCRCDDAT.KEY.CARD_NO);
                CEP.TRC(SCCGWA, WS_I);
                T000_READNEXT_DCTCDDAT();
                if (pgmRtn) return;
                DCCSCINQ.CARD_INFO[WS_I-1].CARD_NO = DCRCDDAT.KEY.CARD_NO;
            }
            T000_ENDBR_DCTCDDAT();
            if (pgmRtn) return;
        } else {
            DCRCDDAT.KEY.CARD_NO = DCCSCINQ.CARD_NO0;
            T000_READ_DCTCDDAT();
            if (pgmRtn) return;
            B031_OUTPUT_TITLE();
            if (pgmRtn) return;
            B032_OUTPUT_DETAIL();
            if (pgmRtn) return;
            DCCSCINQ.CARD_INFO[1-1].CARD_NO = DCRCDDAT.KEY.CARD_NO;
        }
    }
    public void B031_OUTPUT_TITLE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.TITL = " ";
        SCCMPAG.SUBT_ROW_CNT = 0;
        SCCMPAG.MAX_COL_NO = 419;
        SCCMPAG.SCR_ROW_CNT = (short) K_MAX_ROW;
        SCCMPAG.SCR_COL_CNT = (short) K_MAX_COL;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void B032_OUTPUT_DETAIL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_BRW_OUTPUT);
        WS_BRW_OUTPUT.WS_BRW_CARDNO = DCRCDDAT.KEY.CARD_NO;
        WS_BRW_OUTPUT.WS_BRW_CARD_STSW = DCRCDDAT.CARD_STSW;
        WS_BRW_OUTPUT.WS_CARD_LNK_TYP = DCRCDDAT.CARD_LNK_TYP;
        CEP.TRC(SCCGWA, WS_BRW_OUTPUT.WS_BRW_CARDNO);
        CEP.TRC(SCCGWA, WS_BRW_OUTPUT.WS_BRW_CARD_STSW);
        B033_TRANSLATE_CARD_STSW();
        if (pgmRtn) return;
        if (DCRCDDAT.CARD_HLDR_CINO.trim().length() > 0) {
            IBS.init(SCCGWA, CICCUST);
            CICCUST.DATA.CI_NO = DCRCDDAT.CARD_HLDR_CINO;
            CICCUST.FUNC = 'C';
            S000_CALL_CIZCUST();
            if (pgmRtn) return;
            WS_BRW_OUTPUT.WS_BRW_ID_TYP = CICCUST.O_DATA.O_ID_TYPE;
            WS_BRW_OUTPUT.WS_BRW_ID_NO = CICCUST.O_DATA.O_CI_NO;
            if (CICCUST.O_DATA.O_CI_NM.trim().length() > 0) {
                WS_BRW_OUTPUT.WS_BRW_NAME = CICCUST.O_DATA.O_CI_NM;
            } else {
                WS_BRW_OUTPUT.WS_BRW_NAME = CICCUST.O_DATA.O_CI_ENM;
            }
        }
        CEP.TRC(SCCGWA, WS_BRW_OUTPUT.WS_BRW_ID_TYP);
        CEP.TRC(SCCGWA, WS_BRW_OUTPUT.WS_BRW_ID_NO);
        CEP.TRC(SCCGWA, WS_BRW_OUTPUT.WS_BRW_NAME);
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, WS_BRW_OUTPUT);
        SCCMPAG.DATA_LEN = 419;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void B033_TRANSLATE_CARD_STSW() throws IOException,SQLException,Exception {
        if (DCRCDDAT.CARD_STSW == null) DCRCDDAT.CARD_STSW = "";
        JIBS_tmp_int = DCRCDDAT.CARD_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) DCRCDDAT.CARD_STSW += " ";
        if (DCRCDDAT.CARD_STSW.substring(0, 1).equalsIgnoreCase("1")) {
            WS_BRW_OUTPUT.WS_ORL_FLG = 'Y';
        } else {
            WS_BRW_OUTPUT.WS_ORL_FLG = 'N';
        }
        if (DCRCDDAT.CARD_STSW == null) DCRCDDAT.CARD_STSW = "";
        JIBS_tmp_int = DCRCDDAT.CARD_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) DCRCDDAT.CARD_STSW += " ";
        if (DCRCDDAT.CARD_STSW.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("1")) {
            WS_BRW_OUTPUT.WS_FORB_FLG = 'Y';
        } else {
            WS_BRW_OUTPUT.WS_FORB_FLG = 'N';
        }
        if (DCRCDDAT.CARD_STSW == null) DCRCDDAT.CARD_STSW = "";
        JIBS_tmp_int = DCRCDDAT.CARD_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) DCRCDDAT.CARD_STSW += " ";
        if (DCRCDDAT.CARD_STSW.substring(4 - 1, 4 + 1 - 1).equalsIgnoreCase("1")) {
            WS_BRW_OUTPUT.WS_FROZ_FLG = 'Y';
        } else {
            WS_BRW_OUTPUT.WS_FROZ_FLG = 'N';
        }
        if (DCRCDDAT.CARD_STSW == null) DCRCDDAT.CARD_STSW = "";
        JIBS_tmp_int = DCRCDDAT.CARD_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) DCRCDDAT.CARD_STSW += " ";
        if (DCRCDDAT.CARD_STSW.substring(03 - 1, 03 + 1 - 1).equalsIgnoreCase("1")) {
            WS_BRW_OUTPUT.WS_LOST_FLG = 'Y';
        } else {
            WS_BRW_OUTPUT.WS_LOST_FLG = 'N';
        }
        if (DCRCDDAT.CARD_STSW == null) DCRCDDAT.CARD_STSW = "";
        JIBS_tmp_int = DCRCDDAT.CARD_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) DCRCDDAT.CARD_STSW += " ";
        if (DCRCDDAT.CARD_STSW.substring(09 - 1, 09 + 1 - 1).equalsIgnoreCase("1")) {
            WS_BRW_OUTPUT.WS_PINL_FLG = 'Y';
        } else {
            WS_BRW_OUTPUT.WS_PINL_FLG = 'N';
        }
        if (DCRCDDAT.CARD_STSW == null) DCRCDDAT.CARD_STSW = "";
        JIBS_tmp_int = DCRCDDAT.CARD_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) DCRCDDAT.CARD_STSW += " ";
        if (DCRCDDAT.CARD_STSW.substring(10 - 1, 10 + 1 - 1).equalsIgnoreCase("1")) {
            WS_BRW_OUTPUT.WS_SWLW_FLG = 'Y';
        } else {
            WS_BRW_OUTPUT.WS_SWLW_FLG = 'N';
        }
    }
    public void T000_READ_DCTCDDAT() throws IOException,SQLException,Exception {
        DCTCDDAT_RD = new DBParm();
        DCTCDDAT_RD.TableName = "DCTCDDAT";
        IBS.READ(SCCGWA, DCRCDDAT, DCTCDDAT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CARD_NOT_EXIST;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_DCTCDDAT() throws IOException,SQLException,Exception {
        DCTCDDAT_BR.rp = new DBParm();
        DCTCDDAT_BR.rp.TableName = "DCTCDDAT";
        DCTCDDAT_BR.rp.where = "CARD_HLDR_CINO = :DCRCDDAT.CARD_HLDR_CINO "
            + "AND CARD_STS = :DCRCDDAT.CARD_STS";
        IBS.STARTBR(SCCGWA, DCRCDDAT, this, DCTCDDAT_BR);
    }
    public void T000_READNEXT_DCTCDDAT() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, DCRCDDAT, this, DCTCDDAT_BR);
    }
    public void T000_ENDBR_DCTCDDAT() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, DCTCDDAT_BR);
    }
    public void S000_CALL_CIZCUST() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-CISOCUST", CICCUST);
        if (CICCUST.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, CICCUST.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZSMPRD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_BPZSMPRD, BPCSMPRD);
        if (BPCSMPRD.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCSMPRD.RC);
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
