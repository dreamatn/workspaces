package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT4560 {
    String K_OUTPUT_FMT = "BP416";
    String CPN_S_RGNC_MT = "BP-S-RGNC-MAINTAIN  ";
    String CPN_P_QUERY_BANK = "BP-P-QUERY-BANK     ";
    String CPN_P_CHK_CAL_CODE = "BP-P-CHK-CAL-CODE   ";
    String CPN_BP_PARM_READ = "BP-PARM-READ        ";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    BPOT4560_WS_TEMP_CD WS_TEMP_CD = new BPOT4560_WS_TEMP_CD();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCPRMR BPCPRMR = new BPCPRMR();
    BPRPRGTP BPRPRGTP = new BPRPRGTP();
    BPCSRGCM BPCSRGCM = new BPCSRGCM();
    BPCPQBNK BPCPQBNK = new BPCPQBNK();
    BPCOCCAL BPCOCCAL = new BPCOCCAL();
    SCCGWA SCCGWA;
    BPB4580_AWA_4580 BPB4580_AWA_4580;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT4560 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB4580_AWA_4580>");
        BPB4580_AWA_4580 = (BPB4580_AWA_4580) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_CREATE_BANK_RECORD();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPQBNK);
        BPCPQBNK.DATA_INFO.BNK = BPB4580_AWA_4580.BNK;
        S000_CALL_BPZPQBNK();
        if (BPB4580_AWA_4580.CALD_CD.trim().length() > 0) {
            IBS.init(SCCGWA, BPCOCCAL);
            BPCOCCAL.CAL_CODE = BPB4580_AWA_4580.CALD_CD;
            WS_FLD_NO = BPB4580_AWA_4580.CALD_CD_NO;
            S000_CALL_BPZPCCAL();
        }
        if (BPB4580_AWA_4580.RGN_TYPE.trim().length() > 0) {
            IBS.init(SCCGWA, BPRPRGTP);
            IBS.init(SCCGWA, BPCPRMR);
            BPRPRGTP.KEY.TYP = "RGNTP";
            BPRPRGTP.KEY.CD = BPB4580_AWA_4580.RGN_TYPE;
            BPCPRMR.DAT_PTR = BPRPRGTP;
            S000_CALL_BPZPRMR();
        }
    }
    public void B020_CREATE_BANK_RECORD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSRGCM);
        BPCSRGCM.FUNC = 'A';
        BPCSRGCM.OUTPUT_FMT = K_OUTPUT_FMT;
        R000_TRANS_DATA_PARAMETER();
        CEP.TRC(SCCGWA, BPCSRGCM.FUNC);
        S000_CALL_BPZSRGCM();
    }
    public void R000_TRANS_DATA_PARAMETER() throws IOException,SQLException,Exception {
        BPCSRGCM.BNK = BPB4580_AWA_4580.BNK;
        BPCSRGCM.RGN_TYPE = BPB4580_AWA_4580.RGN_TYPE;
        BPCSRGCM.RGN_SEQ = BPB4580_AWA_4580.RGN_SEQ;
        BPCSRGCM.DESC = BPB4580_AWA_4580.RGN_DESC;
        BPCSRGCM.CDSC = BPB4580_AWA_4580.RGN_CDSC;
        BPCSRGCM.REMARK = BPB4580_AWA_4580.REMARK;
        BPCSRGCM.CALD_CD = BPB4580_AWA_4580.CALD_CD;
        if (BPB4580_AWA_4580.EFF_DT < SCCGWA.COMM_AREA.AC_DATE) {
            BPCSRGCM.EFF_DATE = SCCGWA.COMM_AREA.AC_DATE;
        } else {
            BPCSRGCM.EFF_DATE = BPB4580_AWA_4580.EFF_DT;
        }
        BPCSRGCM.EXP_DATE = BPB4580_AWA_4580.EXP_DT;
        BPCSRGCM.OPEN_TM = BPB4580_AWA_4580.OPEN_TM;
        BPCSRGCM.CLOSE_TM = BPB4580_AWA_4580.CLOSE_TM;
        BPCSRGCM.HOPEN_TM = BPB4580_AWA_4580.HOPEN_TM;
        BPCSRGCM.HCLOS_TM = BPB4580_AWA_4580.HCLOS_TM;
    }
    public void S000_CALL_BPZPCCAL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_P_CHK_CAL_CODE, BPCOCCAL);
        if (BPCOCCAL.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCOCCAL.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZPRMR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_BP_PARM_READ, BPCPRMR);
        if (BPCPRMR.RC.RC_RTNCODE != 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_RGN_TYP_NOTFND;
            WS_FLD_NO = BPB4580_AWA_4580.RGN_TYPE_NO;
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZSRGCM() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCALL);
        SCCCALL.CPN_NAME = CPN_S_RGNC_MT;
        SCCCALL.COMMAREA_PTR = BPCSRGCM;
        SCCCALL.ERR_FLDNO = BPB4580_AWA_4580.BNK_NO;
        IBS.CALL(SCCGWA, SCCCALL);
    }
    public void S000_CALL_BPZPQBNK() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_P_QUERY_BANK, BPCPQBNK);
        if (BPCPQBNK.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQBNK.RC);
            WS_FLD_NO = BPB4580_AWA_4580.BNK_NO;
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
