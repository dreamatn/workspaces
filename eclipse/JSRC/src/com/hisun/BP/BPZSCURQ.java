package com.hisun.BP;

import com.hisun.SC.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class BPZSCURQ {
    DBParm BPTINTR_RD;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String CPN_C_INTR_INQ = "BP-C-INTR-INQ       ";
    String CPN_P_INQ_ORG = "BP-P-INQ-ORG      ";
    String CPN_INQUIRE_CCY = "BP-INQUIRE-CCY    ";
    String CPN_P_INQ_PC = "BP-P-INQ-PC       ";
    String CPN_R_BK_MT = "BP-R-IRPD-MAINT";
    String K_RBASE = "RBASE";
    String K_RTENO = "RTENO";
    String K_OUTPUT_FMT = "BPA02";
    String WS_ERR_MSG = " ";
    short WS_I = 0;
    short WS_J = 0;
    short WS_JJ = 0;
    short WS_TENOR_CNT = 0;
    short WS_RECORD_NUMBER = 0;
    int WS_BR = 0;
    String WS_CCY = " ";
    String WS_BASE_TYP = " ";
    int WS_BASETYP_CNT = 0;
    BPZSCURQ_WS_BASETYP_ALL[] WS_BASETYP_ALL = new BPZSCURQ_WS_BASETYP_ALL[20];
    int WS_CCY_CNT = 0;
    BPZSCURQ_WS_CCY_TWENTY[] WS_CCY_TWENTY = new BPZSCURQ_WS_CCY_TWENTY[20];
    BPZSCURQ_WS_TAB_CNT[] WS_TAB_CNT = new BPZSCURQ_WS_TAB_CNT[20];
    BPZSCURQ_WS_RATE_CNT[] WS_RATE_CNT = new BPZSCURQ_WS_RATE_CNT[400];
    String WS_RATE_ARR = " ";
    BPZSCURQ_WS_QUEUE_PROC WS_QUEUE_PROC = new BPZSCURQ_WS_QUEUE_PROC();
    String WS_TENOR = " ";
    char WS_RECORD_FLG = ' ';
    char WS_OUTPUT_FLG = ' ';
    char WS_STOP_FLG = ' ';
    char WS_INT_TENOR_FLG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    BPCCINTI BPCCINTI = new BPCCINTI();
    BPCPQORG BPCPQORG = new BPCPQORG();
    BPCQCCY BPCQCCY = new BPCQCCY();
    BPCOQPCD BPCOQPCD = new BPCOQPCD();
    BPRIRPD BPRIRPD = new BPRIRPD();
    BPRINTR BPRINTR = new BPRINTR();
    SCCGWA SCCGWA;
    BPCSCURQ BPCSCURQ;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public BPZSCURQ() {
        for (int i=0;i<20;i++) WS_BASETYP_ALL[i] = new BPZSCURQ_WS_BASETYP_ALL();
        for (int i=0;i<20;i++) WS_CCY_TWENTY[i] = new BPZSCURQ_WS_CCY_TWENTY();
        for (int i=0;i<20;i++) WS_TAB_CNT[i] = new BPZSCURQ_WS_TAB_CNT();
        for (int i=0;i<400;i++) WS_RATE_CNT[i] = new BPZSCURQ_WS_RATE_CNT();
    }
    public void MP(SCCGWA SCCGWA, BPCSCURQ BPCSCURQ) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCSCURQ = BPCSCURQ;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZSCURQ return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.SC_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.BP_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT();
        if (pgmRtn) return;
        B200_QUERY_PROCESS();
        if (pgmRtn) return;
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCSCURQ.DATA.BR);
        if (BPCSCURQ.DATA.BR == ' ' 
            || BPCSCURQ.DATA.BR == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INT_BR_NO_VALID;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        } else {
            if (BPCSCURQ.DATA.BR == 999999) {
            } else {
                WS_BR = BPCSCURQ.DATA.BR;
                R000_CHECK_BRANCH();
                if (pgmRtn) return;
            }
        }
        CEP.TRC(SCCGWA, BPCSCURQ.DATA.CCY);
        if (BPCSCURQ.DATA.CCY.trim().length() > 0) {
            WS_CCY = BPCSCURQ.DATA.CCY;
            R000_CHECK_CCY();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, BPCSCURQ.DATA.BASE_TYP);
        if (BPCSCURQ.DATA.BASE_TYP.trim().length() > 0) {
            WS_BASE_TYP = BPCSCURQ.DATA.BASE_TYP;
            R000_CHECK_BASE_TYP();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, BPCSCURQ.DATA.TENOR);
        if (BPCSCURQ.DATA.TENOR.trim().length() > 0) {
            WS_TENOR = BPCSCURQ.DATA.TENOR;
            R000_CHECK_TENOR();
            if (pgmRtn) return;
        }
    }
    public void B200_QUERY_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRINTR);
        BPRINTR.KEY.BR = BPCSCURQ.DATA.BR;
        BPRINTR.KEY.CCY = BPCSCURQ.DATA.CCY;
        BPRINTR.KEY.BASE_TYP = BPCSCURQ.DATA.BASE_TYP;
        BPRINTR.KEY.TENOR = BPCSCURQ.DATA.TENOR;
        T000_READ_BPTINTR();
        if (pgmRtn) return;
        if (BPCSCURQ.RETURN_INFO == 'F') {
            B030_01_DATA_TRANS_TO_FMT();
            if (pgmRtn) return;
            B030_02_DATA_OUTPUT_FMT();
            if (pgmRtn) return;
        } else {
            if (BPCSCURQ.RETURN_INFO == 'N') {
                if ("999999".trim().length() == 0) BPRINTR.KEY.BR = 0;
                else BPRINTR.KEY.BR = Integer.parseInt("999999");
                T000_READ_BPTINTR();
                if (pgmRtn) return;
                if (BPCSCURQ.RETURN_INFO == 'F') {
                    B030_01_DATA_TRANS_TO_FMT();
                    if (pgmRtn) return;
                    B030_02_DATA_OUTPUT_FMT();
                    if (pgmRtn) return;
                } else {
                    CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_RECORD_NOTFND);
                }
            } else {
                CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_RECORD_NOTFND);
            }
        }
    }
    public void B030_01_DATA_TRANS_TO_FMT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSCURQ);
        BPCSCURQ.DATA.BR = BPRINTR.KEY.BR;
        BPCSCURQ.DATA.CCY = BPRINTR.KEY.CCY;
        BPCSCURQ.DATA.BASE_TYP = BPRINTR.KEY.BASE_TYP;
        BPCSCURQ.DATA.TENOR = BPRINTR.KEY.TENOR;
        BPCSCURQ.DATA.RATE = BPRINTR.RATE;
    }
    public void B030_02_DATA_OUTPUT_FMT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = BPCSCURQ.DATA;
        SCCFMT.DATA_LEN = 29;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void R000_CHECK_BRANCH() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPQORG);
        BPCPQORG.BR = WS_BR;
        CEP.TRC(SCCGWA, BPCPQORG.BR);
        IBS.CALLCPN(SCCGWA, CPN_P_INQ_ORG, BPCPQORG);
        if (BPCPQORG.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void R000_CHECK_CCY() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCQCCY);
        BPCQCCY.DATA.CCY = WS_CCY;
        IBS.CALLCPN(SCCGWA, CPN_INQUIRE_CCY, BPCQCCY);
        if (BPCQCCY.RC.RTNCODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCQCCY.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void R000_CHECK_BASE_TYP() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCOQPCD);
        BPCOQPCD.INPUT_DATA.TYPE = K_RBASE;
        BPCOQPCD.INPUT_DATA.CODE = WS_BASE_TYP;
        IBS.CALLCPN(SCCGWA, CPN_P_INQ_PC, BPCOQPCD);
        if (BPCOQPCD.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCOQPCD.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void R000_CHECK_TENOR() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCOQPCD);
        BPCOQPCD.INPUT_DATA.TYPE = K_RTENO;
        BPCOQPCD.INPUT_DATA.CODE = WS_TENOR;
        IBS.CALLCPN(SCCGWA, CPN_P_INQ_PC, BPCOQPCD);
        if (BPCOQPCD.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCOQPCD.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_BPTINTR() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "READ DATA START");
        CEP.TRC(SCCGWA, BPRINTR.KEY.BR);
        CEP.TRC(SCCGWA, BPRINTR.KEY.CCY);
        CEP.TRC(SCCGWA, BPRINTR.KEY.BASE_TYP);
        CEP.TRC(SCCGWA, BPRINTR.KEY.TENOR);
        BPTINTR_RD = new DBParm();
        BPTINTR_RD.TableName = "BPTINTR";
        BPTINTR_RD.where = "BR = :BPRINTR.KEY.BR "
            + "AND CCY = :BPRINTR.KEY.CCY "
            + "AND BASE_TYP = :BPRINTR.KEY.BASE_TYP "
            + "AND TENOR = :BPRINTR.KEY.TENOR";
        BPTINTR_RD.fst = true;
        IBS.READ(SCCGWA, BPRINTR, this, BPTINTR_RD);
        CEP.TRC(SCCGWA, "READ DATA END");
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CEP.TRC(SCCGWA, "NORMAL");
            BPCSCURQ.RETURN_INFO = 'F';
            BPCSCURQ.RC.RC_CODE = 0;
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.TRC(SCCGWA, "NOTFND");
            BPCSCURQ.RETURN_INFO = 'N';
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_RECORD_NOTFND, BPCSCURQ.RC);
        } else {
        }
        CEP.TRC(SCCGWA, "END READ");
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
