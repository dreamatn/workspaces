package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT2122 {
    int JIBS_tmp_int;
    DBParm BPTORGR_RD;
    String JIBS_tmp_str[] = new String[10];
    String CPN_S_CSHAPP_MAINTAIN = "BP-S-CSHAPP-MAINTAIN";
    String CPN_F_TLR_INF_QUERY = "BP-F-TLR-INF-QUERY";
    String CPN_F_TLR_QRY_BR_CHK = "BP-F-TLR-QRY-BR-CHK";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    short WS_IDX = 0;
    char WS_FOUND1_FLG = ' ';
    char WS_FOUND2_FLG = ' ';
    char WS_FUNC_FLG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPRORGR BPRORGR = new BPRORGR();
    BPCOLIBB BPCOLIBB = new BPCOLIBB();
    BPCFTLRQ BPCFTLRQ = new BPCFTLRQ();
    BPCFTLCM BPCFTLCM = new BPCFTLCM();
    SCCGWA SCCGWA;
    BPB2122_AWA_2122 BPB2122_AWA_2122;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCOWA SCCOWA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERROR;
        S000_ERR_MSG_PROC_LAST();
        CEP.TRC(SCCGWA, "BPOT2122 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB2122_AWA_2122>");
        BPB2122_AWA_2122 = (BPB2122_AWA_2122) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPB2122_AWA_2122.APP_BR);
        CEP.TRC(SCCGWA, BPB2122_AWA_2122.UP_BR);
        CEP.TRC(SCCGWA, BPB2122_AWA_2122.APP_TYPE);
        CEP.TRC(SCCGWA, BPB2122_AWA_2122.APP_STS);
        CEP.TRC(SCCGWA, BPB2122_AWA_2122.BEG_DT);
        CEP.TRC(SCCGWA, BPB2122_AWA_2122.END_DT);
        CEP.TRC(SCCGWA, BPB2122_AWA_2122.ALLOT_TP);
        if (SCCGWA.COMM_AREA.TR_BANK.equalsIgnoreCase("043")) {
            B010_CHECK_INPUT();
            B020_BROWSE_CASH_APP_INFO();
        } else {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR);
        }
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFTLRQ);
        BPCFTLRQ.INFO.TLR = SCCGWA.COMM_AREA.TL_ID;
        S000_CALL_BPZFTLRQ();
        if (BPCFTLRQ.INFO.TLR_STSW == null) BPCFTLRQ.INFO.TLR_STSW = "";
        JIBS_tmp_int = BPCFTLRQ.INFO.TLR_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) BPCFTLRQ.INFO.TLR_STSW += " ";
        if (!BPCFTLRQ.INFO.TLR_STSW.substring(4 - 1, 4 + 1 - 1).equalsIgnoreCase("1") 
            && BPCFTLRQ.INFO.TX_LVL == '0') {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_TLR_HAS_NO_AUTH);
        }
        CEP.TRC(SCCGWA, BPB2122_AWA_2122.APP_BR);
        if (BPB2122_AWA_2122.APP_BR != 0 
            && BPB2122_AWA_2122.APP_BR != SCCGWA.COMM_AREA.BR_DP.TR_BRANCH) {
            WS_FOUND1_FLG = 'N';
            WS_FOUND2_FLG = 'N';
            IBS.init(SCCGWA, BPRORGR);
            BPRORGR.KEY.TYP = "07";
            BPRORGR.KEY.BR = BPB2122_AWA_2122.APP_BR;
            BPRORGR.REL_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            BPTORGR_RD = new DBParm();
            BPTORGR_RD.TableName = "BPTORGR";
            BPTORGR_RD.where = "TYP = :BPRORGR.KEY.TYP "
                + "AND BR = :BPRORGR.KEY.BR "
                + "AND REL_BR = :BPRORGR.REL_BR";
            IBS.READ(SCCGWA, BPRORGR, this, BPTORGR_RD);
            CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
            if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
                WS_FOUND1_FLG = 'Y';
            } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            }
            IBS.init(SCCGWA, BPRORGR);
            BPRORGR.KEY.TYP = "07";
            BPRORGR.KEY.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            BPRORGR.REL_BR = BPB2122_AWA_2122.APP_BR;
            BPTORGR_RD = new DBParm();
            BPTORGR_RD.TableName = "BPTORGR";
            BPTORGR_RD.where = "TYP = :BPRORGR.KEY.TYP "
                + "AND BR = :BPRORGR.KEY.BR "
                + "AND REL_BR = :BPRORGR.REL_BR";
            IBS.READ(SCCGWA, BPRORGR, this, BPTORGR_RD);
            CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
            if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
                WS_FOUND2_FLG = 'Y';
            } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            }
            if (WS_FOUND1_FLG == 'N' 
                && WS_FOUND2_FLG == 'N') {
                CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_NOT_RLT_BRANCH);
            }
        }
    }
    public void B020_BROWSE_CASH_APP_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCOLIBB);
        BPCOLIBB.FUNC = 'B';
        R000_TRANS_DATA_PARAMETER();
        S000_CALL_BPZSLIBB();
    }
    public void S000_CALL_BPZFTLCM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_F_TLR_QRY_BR_CHK, BPCFTLCM);
        if (BPCFTLCM.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, BPCFTLCM.RC.RC_CODE);
        }
    }
    public void R000_TRANS_DATA_PARAMETER() throws IOException,SQLException,Exception {
        BPCOLIBB.APP_NO = BPB2122_AWA_2122.APP_NO;
        BPCOLIBB.APP_BR = BPB2122_AWA_2122.APP_BR;
        BPCOLIBB.UP_BR = BPB2122_AWA_2122.UP_BR;
        BPCOLIBB.APP_TYPE = BPB2122_AWA_2122.APP_TYPE;
        BPCOLIBB.APP_STS = BPB2122_AWA_2122.APP_STS;
        BPCOLIBB.BEG_DT = BPB2122_AWA_2122.BEG_DT;
        BPCOLIBB.END_DT = BPB2122_AWA_2122.END_DT;
        BPCOLIBB.ALLOT_TP = BPB2122_AWA_2122.ALLOT_TP;
    }
    public void S000_CALL_BPZSLIBB() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_S_CSHAPP_MAINTAIN, BPCOLIBB);
    }
    public void S000_CALL_BPZFTLRQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_F_TLR_INF_QUERY, BPCFTLRQ);
        if (BPCFTLRQ.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCFTLRQ.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        CEP.ERRC(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_LAST() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
