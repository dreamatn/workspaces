package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT2910 {
    brParm BPTORGR_BR = new brParm();
    String JIBS_tmp_str[] = new String[10];
    String CPN_S_BVAPP_MAINTAIN = "BP-S-BVAPP-MAINTAIN";
    String CPN_F_TLR_INF_QUERY = "BP-F-TLR-INF-QUERY";
    String CPN_F_TLR_QRY_BR_CHK = "BP-F-TLR-QRY-BR-CHK";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    short WS_IDX = 0;
    char WS_FUNC_FLG = ' ';
    char WS_FOUND1_FLG = ' ';
    char WS_FOUND2_FLG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPRORGR BPRORGR = new BPRORGR();
    BPCOAPLL BPCOAPLL = new BPCOAPLL();
    BPCFTLRQ BPCFTLRQ = new BPCFTLRQ();
    BPCFTLCM BPCFTLCM = new BPCFTLCM();
    SCCGWA SCCGWA;
    BPB2910_AWA_2910 BPB2910_AWA_2910;
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
        CEP.TRC(SCCGWA, "BPOT2910 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB2910_AWA_2910>");
        BPB2910_AWA_2910 = (BPB2910_AWA_2910) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPB2910_AWA_2910.APP_BR);
        CEP.TRC(SCCGWA, BPB2910_AWA_2910.UP_BR);
        CEP.TRC(SCCGWA, BPB2910_AWA_2910.APP_TYPE);
        CEP.TRC(SCCGWA, BPB2910_AWA_2910.APP_STS);
        CEP.TRC(SCCGWA, BPB2910_AWA_2910.BEG_DT);
        CEP.TRC(SCCGWA, BPB2910_AWA_2910.END_DT);
        B010_CHECK_INPUT();
        B020_BROWSE_BV_APP_INFO();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFTLRQ);
        BPCFTLRQ.INFO.TLR = SCCGWA.COMM_AREA.TL_ID;
        S000_CALL_BPZFTLRQ();
        if (BPB2910_AWA_2910.BEG_DT != 0 
            && BPB2910_AWA_2910.END_DT != 0) {
            if (BPB2910_AWA_2910.END_DT < BPB2910_AWA_2910.BEG_DT) {
                CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_STR_GT_END);
            }
        }
        if ((BPB2910_AWA_2910.APP_BR != SCCGWA.COMM_AREA.BR_DP.TR_BRANCH) 
            && (BPB2910_AWA_2910.APP_BR != 0)) {
            WS_FOUND1_FLG = 'N';
            WS_FOUND2_FLG = 'N';
            IBS.init(SCCGWA, BPRORGR);
            BPRORGR.KEY.TYP = "09";
            BPRORGR.KEY.BR = BPB2910_AWA_2910.APP_BR;
            BPRORGR.REL_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            BPTORGR_BR.rp = new DBParm();
            BPTORGR_BR.rp.TableName = "BPTORGR";
            BPTORGR_BR.rp.where = "TYP = :BPRORGR.KEY.TYP "
                + "AND BR = :BPRORGR.KEY.BR "
                + "AND REL_BR = :BPRORGR.REL_BR";
            IBS.STARTBR(SCCGWA, BPRORGR, this, BPTORGR_BR);
            while (SCCGWA.COMM_AREA.DBIO_FLG != '1') {
                IBS.READNEXT(SCCGWA, BPRORGR, this, BPTORGR_BR);
                CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
                if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
                    WS_FOUND1_FLG = 'Y';
                } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
                }
            }
            IBS.ENDBR(SCCGWA, BPTORGR_BR);
            IBS.init(SCCGWA, BPRORGR);
            BPRORGR.KEY.TYP = "09";
            BPRORGR.KEY.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            BPRORGR.REL_BR = BPB2910_AWA_2910.APP_BR;
            BPTORGR_BR.rp = new DBParm();
            BPTORGR_BR.rp.TableName = "BPTORGR";
            BPTORGR_BR.rp.where = "TYP = :BPRORGR.KEY.TYP "
                + "AND BR = :BPRORGR.KEY.BR "
                + "AND REL_BR = :BPRORGR.REL_BR";
            IBS.STARTBR(SCCGWA, BPRORGR, this, BPTORGR_BR);
            while (SCCGWA.COMM_AREA.DBIO_FLG != '1') {
                IBS.READNEXT(SCCGWA, BPRORGR, this, BPTORGR_BR);
                if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
                    WS_FOUND1_FLG = 'Y';
                } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
                }
            }
            IBS.ENDBR(SCCGWA, BPTORGR_BR);
            if (WS_FOUND1_FLG == 'N' 
                && WS_FOUND2_FLG == 'N') {
                CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_NOT_RLT_BRANCH);
            }
        }
    }
    public void B020_BROWSE_BV_APP_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCOAPLL);
        BPCOAPLL.FUNC = 'B';
        R000_TRANS_DATA_PARAMETER();
        S000_CALL_BPZSAPLL();
    }
    public void S000_CALL_BPZFTLCM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_F_TLR_QRY_BR_CHK, BPCFTLCM);
        if (BPCFTLCM.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, BPCFTLCM.RC.RC_CODE);
        }
    }
    public void R000_TRANS_DATA_PARAMETER() throws IOException,SQLException,Exception {
        BPCOAPLL.APP_NO = BPB2910_AWA_2910.APP_NO;
        BPCOAPLL.APP_BR = BPB2910_AWA_2910.APP_BR;
        BPCOAPLL.BR = BPB2910_AWA_2910.UP_BR;
        BPCOAPLL.APP_TYPE = BPB2910_AWA_2910.APP_TYPE;
        BPCOAPLL.APP_STS = BPB2910_AWA_2910.APP_STS;
        BPCOAPLL.BEG_DT = BPB2910_AWA_2910.BEG_DT;
        BPCOAPLL.END_DT = BPB2910_AWA_2910.END_DT;
        CEP.TRC(SCCGWA, BPCOAPLL.BR);
        if (BPB2910_AWA_2910.BV_CODE.trim().length() > 0 
            || !BPB2910_AWA_2910.BV_CODE.equalsIgnoreCase("0")) {
            BPCOAPLL.BV_INFO[1-1].BV_CODE = BPB2910_AWA_2910.BV_CODE;
        }
        CEP.TRC(SCCGWA, BPCOAPLL.BV_INFO[1-1].BV_CODE);
    }
    public void S000_CALL_BPZSAPLL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_S_BVAPP_MAINTAIN, BPCOAPLL);
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
