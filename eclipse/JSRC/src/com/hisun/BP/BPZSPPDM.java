package com.hisun.BP;

import com.hisun.SC.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class BPZSPPDM {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String K_HIS_REMARKS = "BPTAPPD INFOMATION MAINTAIN";
    String K_CPY_BPRAPPD = "BPRAPPD";
    String CPN_R_ADW_APPD = "BP-R-ADW-APPD";
    String CPN_R_BRE_MVBB = "BP-R-BRE-MVBB";
    String CPN_S_GET_SEQ = "BP-S-GET-SEQ";
    String CPN_REC_NHIS = "BP-REC-NHIS";
    String CPN_R_STARTBR_APPD = "BP-R-STARTBR-APPD";
    int K_P_CNT = 20;
    char K_RUN_MODE = 'O';
    short K_NUM = 1;
    String K_BPFBAS_SEQ = "BCINO";
    String K_SEQ_TYPE = "SEQ";
    String WS_ERR_MSG = " ";
    short WS_IDX = 0;
    int WS_BR = 0;
    int WS_I = 0;
    int WS_CNT = 0;
    int WS_CNT1 = 0;
    int WS_NO = 0;
    char WS_TBL_TXIF_FLAG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    BPCTPPDF BPCTPPDF = new BPCTPPDF();
    BPCRAPPD BPCRAPPD = new BPCRAPPD();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    BPCTMVBB BPCTMVBB = new BPCTMVBB();
    BPCOPPDO BPCOPPDO = new BPCOPPDO();
    BPCOAPPO BPCOAPPO = new BPCOAPPO();
    BPCSGSEQ BPCSGSEQ = new BPCSGSEQ();
    BPRAPPD BPRAPPD = new BPRAPPD();
    BPRAPPD BPROPPD = new BPRAPPD();
    SCCGWA SCCGWA;
    BPCOPPDM BPCOPPDM;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, BPCOPPDM BPCOPPDM) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCOPPDM = BPCOPPDM;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZSPPDM return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.SC_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.BP_AREA_PTR;
        IBS.init(SCCGWA, BPCTPPDF);
        IBS.init(SCCGWA, BPCRAPPD);
        IBS.init(SCCGWA, BPCOPPDM.RC);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCOPPDM.FUNC);
        if (BPCOPPDM.FUNC == 'A') {
            B010_ADD_PROCESS();
            if (pgmRtn) return;
            R000_TRANS_DATA_OUTPUT();
            if (pgmRtn) return;
        } else if (BPCOPPDM.FUNC == 'I') {
            B020_QUERY_PROCESS();
            if (pgmRtn) return;
            R000_TRANS_DATA_OUTPUT();
            if (pgmRtn) return;
        } else if (BPCOPPDM.FUNC == 'B') {
            B030_QUERY_DATA_PROCESS();
            if (pgmRtn) return;
        } else {
            CEP.TRC(SCCGWA, "ERR");
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERROR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B010_ADD_PROCESS() throws IOException,SQLException,Exception {
        B010_ADD_APPD_NO();
        if (pgmRtn) return;
        B010_CHECK_BOXPPD_EXIST();
        if (pgmRtn) return;
        if (BPCTPPDF.RETURN_INFO == 'F') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_TLR_VABX_EXIST;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCTPPDF.RC);
        if (JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_APPD_NOTFND)) {
            IBS.init(SCCGWA, BPRAPPD);
            R000_TRANS_DATA_PARAMETER();
            if (pgmRtn) return;
            BPCTPPDF.POINTER = BPRAPPD;
            BPCTPPDF.LEN = 449;
            BPCTPPDF.INFO.FUNC = 'A';
            S000_CALL_BPZTPPDF();
            if (pgmRtn) return;
            if (BPCTPPDF.RC.RC_CODE != 0) {
                WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCTPPDF.RC);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            B010_01_HISTORY_RECORD();
            if (pgmRtn) return;
            R010_TRANS_DATA_OUPUT();
            if (pgmRtn) return;
        }
    }
    public void B010_01_HISTORY_RECORD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'A';
        BPCPNHIS.INFO.FMT_ID = K_CPY_BPRAPPD;
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_RMK = K_HIS_REMARKS;
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void B010_ADD_APPD_NO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSGSEQ);
        BPCSGSEQ.TYPE = K_SEQ_TYPE;
        BPCSGSEQ.CODE = K_BPFBAS_SEQ;
        BPCSGSEQ.AC_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCSGSEQ.RUN_MODE = K_RUN_MODE;
        BPCSGSEQ.NUM = K_NUM;
        S000_CALL_BPZSGSEQ();
        if (pgmRtn) return;
        BPCOPPDM.APT_NO = (int) BPCSGSEQ.SEQ;
    }
    public void B010_CHECK_BOXPPD_EXIST() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRAPPD);
        BPRAPPD.KEY.APT_NO = BPCOPPDM.APT_NO;
        BPRAPPD.KEY.APT_CCY = BPCOPPDM.APT_CCY;
        BPCTPPDF.POINTER = BPRAPPD;
        BPCTPPDF.LEN = 449;
        BPCTPPDF.INFO.FUNC = 'Q';
        S000_CALL_BPZTPPDF();
        if (pgmRtn) return;
    }
    public void B020_QUERY_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRAPPD);
        CEP.TRC(SCCGWA, BPCOPPDM.APT_NO);
        BPRAPPD.KEY.APT_NO = BPCOPPDM.APT_NO;
        BPRAPPD.KEY.APT_CCY = BPCOPPDM.APT_CCY;
        BPCTPPDF.POINTER = BPRAPPD;
        BPCTPPDF.LEN = 449;
        BPCTPPDF.INFO.FUNC = 'Q';
        S000_CALL_BPZTPPDF();
        if (pgmRtn) return;
        if (BPCTPPDF.RETURN_INFO == 'N') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_APPD_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        R010_TRANS_DATA_OUPUT();
        if (pgmRtn) return;
    }
    public void B030_QUERY_DATA_PROCESS() throws IOException,SQLException,Exception {
        B030_STARTBR_PROCESS();
        if (pgmRtn) return;
        B030_READNEXT_PROCESS();
        if (pgmRtn) return;
        WS_CNT = 0;
        while (WS_TBL_TXIF_FLAG != 'D' 
            && WS_CNT <= 1000 
            && SCCMPAG.FUNC != 'E') {
            CEP.TRC(SCCGWA, "11111");
            if (WS_TBL_TXIF_FLAG == 'N') {
                CEP.TRC(SCCGWA, "22222");
                WS_CNT = WS_CNT + 1;
                if (WS_CNT == 1) {
                    B030_01_OUT_TITLE();
                    if (pgmRtn) return;
                }
                B030_02_OUT_BRW_DATA();
                if (pgmRtn) return;
                B030_READNEXT_PROCESS();
                if (pgmRtn) return;
            }
        }
        B030_ENDBR_PROCESS();
        if (pgmRtn) return;
        BPCOPPDM.CNT = WS_CNT;
    }
    public void R000_TRANS_DATA_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = BPCOPPDM.OUTPUT_FMT;
        SCCFMT.DATA_PTR = BPCOPPDO;
        SCCFMT.DATA_LEN = 366;
        CEP.TRC(SCCGWA, BPCOPPDO);
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void B030_02_OUT_BRW_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCOAPPO);
        BPCOAPPO.APT_NO = BPRAPPD.KEY.APT_NO;
        BPCOAPPO.APT_CINO = BPRAPPD.APT_CINO;
        BPCOAPPO.CONTACT = BPRAPPD.CONTACT;
        BPCOAPPO.APT_DT = BPRAPPD.APT_DT;
        BPCOAPPO.APT_CCY = BPRAPPD.KEY.APT_CCY;
        BPCOAPPO.APT_AMT = BPRAPPD.APT_AMT;
        BPCOAPPO.APT_WDDT = BPRAPPD.APT_WDDT;
        BPCOAPPO.APT_BR = BPRAPPD.APT_BR;
        BPCOAPPO.CONT_PN = BPRAPPD.CONT_PN;
        CEP.TRC(SCCGWA, "OUT");
        CEP.TRC(SCCGWA, BPCOAPPO.CONT_PN);
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, BPCOAPPO);
        SCCMPAG.DATA_LEN = 331;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void R000_TRANS_DATA_PARAMETER() throws IOException,SQLException,Exception {
        BPRAPPD.KEY.APT_NO = BPCOPPDM.APT_NO;
        BPRAPPD.APT_CINO = BPCOPPDM.APT_CINO;
        BPRAPPD.APT_DT = BPCOPPDM.APT_DT;
        BPRAPPD.APT_TM = BPCOPPDM.APT_TM;
        BPRAPPD.APT_WDDT = BPCOPPDM.APT_WDDT;
        BPRAPPD.APT_AMT = BPCOPPDM.APT_AMT;
        BPRAPPD.KEY.APT_CCY = BPCOPPDM.APT_CCY;
        BPRAPPD.APT_CHNL = BPCOPPDM.APT_CHNL;
        BPRAPPD.CONTACT = BPCOPPDM.CONTACT;
        BPRAPPD.CONT_PN = BPCOPPDM.CONT_PN;
        BPRAPPD.STS = '0';
        BPRAPPD.APT_BR = BPCOPPDM.APT_BR;
        BPRAPPD.UPD_DT = BPCOPPDM.UPD_DT;
        BPRAPPD.UPD_TLR = BPCOPPDM.UPD_TLR;
        BPRAPPD.OPEN_DT = BPCOPPDM.UPD_DT;
        BPRAPPD.OPEN_TLR = BPCOPPDM.UPD_TLR;
        CEP.TRC(SCCGWA, BPCOPPDM.CONTACT);
        CEP.TRC(SCCGWA, BPRAPPD.CONTACT);
    }
    public void R010_TRANS_DATA_OUPUT() throws IOException,SQLException,Exception {
        BPCOPPDO.APT_NO = BPRAPPD.KEY.APT_NO;
        BPCOPPDO.APT_CINO = BPRAPPD.APT_CINO;
        BPCOPPDO.APT_DT = BPRAPPD.APT_DT;
        BPCOPPDO.APT_TM = BPRAPPD.APT_TM;
        BPCOPPDO.APT_WDDT = BPRAPPD.APT_WDDT;
        BPCOPPDO.APT_AMT = BPRAPPD.APT_AMT;
        BPCOPPDO.APT_CHNL = BPRAPPD.APT_CHNL;
        BPCOPPDO.APT_BR = BPRAPPD.APT_BR;
        BPCOPPDO.CONTACT = BPRAPPD.CONTACT;
        BPCOPPDO.CONT_PN = BPRAPPD.CONT_PN;
        BPCOPPDO.CASH_TYP = "01";
        BPCOPPDO.APT_CCY = BPRAPPD.KEY.APT_CCY;
        BPCOPPDO.STS = BPRAPPD.STS;
        BPCOPPDO.UPD_DT = BPRAPPD.UPD_DT;
        BPCOPPDO.UPD_TLR = BPRAPPD.UPD_TLR;
    }
    public void B030_STARTBR_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRAPPD);
        IBS.init(SCCGWA, BPCRAPPD);
        CEP.TRC(SCCGWA, "11111111");
        BPRAPPD.APT_CINO = BPCOPPDM.APT_CINO;
        BPRAPPD.APT_DT = BPCOPPDM.APT_DT;
        BPRAPPD.APT_WDDT = BPCOPPDM.APT_WDDT;
        BPRAPPD.APT_BR = BPCOPPDM.APT_BR;
        if (BPCOPPDM.APT_CINO.trim().length() == 0 
                && BPRAPPD.APT_DT == 0 
                && BPCOPPDM.APT_WDDT == 0) {
            BPCRAPPD.INFO.FUNC = '6';
        } else if (BPCOPPDM.APT_CINO.trim().length() > 0 
                && BPRAPPD.APT_DT == 0 
                && BPCOPPDM.APT_WDDT == 0) {
            BPCRAPPD.INFO.FUNC = '1';
        } else if (BPCOPPDM.APT_CINO.trim().length() == 0 
                && BPRAPPD.APT_DT != 0 
                && BPCOPPDM.APT_WDDT == 0) {
            BPCRAPPD.INFO.FUNC = '2';
        } else if (BPCOPPDM.APT_CINO.trim().length() == 0 
                && BPRAPPD.APT_DT == 0 
                && BPCOPPDM.APT_WDDT != 0) {
            BPCRAPPD.INFO.FUNC = '8';
        } else if (BPCOPPDM.APT_CINO.trim().length() > 0 
                && BPRAPPD.APT_DT == 0 
                && BPCOPPDM.APT_WDDT != 0) {
            BPCRAPPD.INFO.FUNC = '5';
        } else if (BPCOPPDM.APT_CINO.trim().length() == 0 
                && BPRAPPD.APT_DT != 0 
                && BPCOPPDM.APT_WDDT != 0) {
            BPCRAPPD.INFO.FUNC = '4';
        } else if (BPCOPPDM.APT_CINO.trim().length() > 0 
                && BPRAPPD.APT_DT != 0 
                && BPCOPPDM.APT_WDDT == 0) {
            BPCRAPPD.INFO.FUNC = '3';
        } else if (BPCOPPDM.APT_CINO.trim().length() > 0 
                && BPRAPPD.APT_DT != 0 
                && BPCOPPDM.APT_WDDT != 0) {
            BPCRAPPD.INFO.FUNC = '7';
        } else {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERROR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        BPCRAPPD.INFO.LEN = 449;
        BPCRAPPD.INFO.POINTER = BPRAPPD;
        S000_CALL_BPZRAPPD();
        if (pgmRtn) return;
    }
    public void B030_READNEXT_PROCESS() throws IOException,SQLException,Exception {
        BPCRAPPD.INFO.FUNC = 'N';
        BPCRAPPD.INFO.POINTER = BPRAPPD;
        BPCRAPPD.INFO.LEN = 449;
        S000_CALL_BPZRAPPD();
        if (pgmRtn) return;
        if (BPCRAPPD.RETURN_INFO == 'F') {
            WS_TBL_TXIF_FLAG = 'N';
        } else if (BPCRAPPD.RETURN_INFO == 'N') {
            WS_TBL_TXIF_FLAG = 'D';
        } else {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_DATE_RANGE_ERR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B030_ENDBR_PROCESS() throws IOException,SQLException,Exception {
        BPCRAPPD.INFO.FUNC = 'E';
        BPCRAPPD.INFO.LEN = 449;
        BPCRAPPD.INFO.POINTER = BPRAPPD;
        S000_CALL_BPZRAPPD();
        if (pgmRtn) return;
        if (BPCRAPPD.RETURN_INFO != 'F') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_DATE_RANGE_ERR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZRAPPD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_R_STARTBR_APPD, BPCRAPPD);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void S000_CALL_BPZTPPDF() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_R_ADW_APPD, BPCTPPDF);
    }
    public void S000_CALL_BPZTMVBB() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_R_BRE_MVBB, BPCTMVBB);
        if (BPCTMVBB.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCTMVBB.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_REC_NHIS, BPCPNHIS);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, BPCPNHIS.RC.RC_CODE);
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPNHIS.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZSGSEQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_S_GET_SEQ, BPCSGSEQ);
        CEP.TRC(SCCGWA, BPCSGSEQ.RC);
        if (BPCSGSEQ.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCSGSEQ.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B030_01_OUT_TITLE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.TITL = " ";
        SCCMPAG.SUBT_ROW_CNT = 0;
        SCCMPAG.MAX_COL_NO = 331;
        SCCMPAG.SCR_ROW_CNT = 30;
        SCCMPAG.SCR_COL_CNT = 6;
        B_MPAG();
        if (pgmRtn) return;
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
