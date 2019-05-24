package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT4586 {
    String JIBS_tmp_str[] = new String[10];
    String CPN_S_RGND_MT = "BP-S-RGND-MAINTAIN  ";
    String CPN_P_QUERY_BANK = "BP-P-QUERY-BANK     ";
    String CPN_P_QUERY_RGND = "BP-P-INQ-RGND ";
    String CPN_BP_ORG_READ = "BP-P-INQ-ORG ";
    String CPN_BP_PARM_READ = "BP-PARM-READ  ";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    String WS_TEXT = " ";
    BPOT4586_WS_TYPE_CD WS_TYPE_CD = new BPOT4586_WS_TYPE_CD();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPRPRGTP BPRPRGTP = new BPRPRGTP();
    BPCPRMR BPCPRMR = new BPCPRMR();
    BPCPQBNK BPCPQBNK = new BPCPQBNK();
    BPCPQRGD BPCPQRGD = new BPCPQRGD();
    BPCPQORG BPCPQORG = new BPCPQORG();
    BPCSRGND BPCSRGND = new BPCSRGND();
    SCCGWA SCCGWA;
    BPB4580_AWA_4580 BPB4580_AWA_4580;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT4586 return!");
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
        B020_BROWSE_RGND_RECORD();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPQBNK);
        BPCPQBNK.DATA_INFO.BNK = BPB4580_AWA_4580.BNK;
        S000_CALL_BPZPQBNK();
        if (BPB4580_AWA_4580.RGN_TYPE.equalsIgnoreCase("0") 
            || BPB4580_AWA_4580.RGN_TYPE.charAt(0) == 0X00 
            || BPB4580_AWA_4580.RGN_TYPE.trim().length() == 0) {
        } else {
            IBS.init(SCCGWA, BPRPRGTP);
            IBS.init(SCCGWA, BPCPRMR);
            BPRPRGTP.KEY.TYP = "RGNTP";
            WS_TYPE_CD.WS_BNK = BPB4580_AWA_4580.BNK;
            CEP.TRC(SCCGWA, BPB4580_AWA_4580.RGN_TYPE);
            BPRPRGTP.KEY.CD = BPB4580_AWA_4580.RGN_TYPE;
            BPCPRMR.DAT_PTR = BPRPRGTP;
            S000_CALL_BPZPRMR();
        }
    }
    public void B020_BROWSE_RGND_RECORD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSRGND);
        BPCSRGND.FUNC = 'B';
        BPCSRGND.BNK = BPB4580_AWA_4580.BNK;
        BPCSRGND.RGN_TYPE = BPB4580_AWA_4580.RGN_TYPE;
        BPCSRGND.RGN_SEQ = BPB4580_AWA_4580.RGN_SEQ;
        BPCSRGND.UNIT = BPB4580_AWA_4580.RGN_UNIT;
        CEP.TRC(SCCGWA, BPB4580_AWA_4580.BNK);
        S000_CALL_BPZSRGND();
    }
    public void S000_CALL_BPZSRGND() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCALL);
        SCCCALL.CPN_NAME = CPN_S_RGND_MT;
        SCCCALL.COMMAREA_PTR = BPCSRGND;
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
    public void S000_CALL_BPZPRMR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_BP_PARM_READ, BPCPRMR);
        if (BPCPRMR.RC.RC_RTNCODE != 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_RGN_TYP_NOTFND;
            WS_FLD_NO = BPB4580_AWA_4580.RGN_TYPE_NO;
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZPQRGD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_P_QUERY_RGND, BPCPQRGD);
        JIBS_tmp_str[1] = IBS.CLS2CPY(SCCGWA, BPCPQRGD.RC);
        if (BPCPQRGD.RC.RC_CODE != 0 
            && !JIBS_tmp_str[1].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_RECORD_NOTFND)) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQRGD.RC);
            WS_FLD_NO = BPB4580_AWA_4580.BNK_NO;
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZPQORG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_BP_ORG_READ, BPCPQORG);
        if (BPCPQORG.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
            WS_FLD_NO = BPB4580_AWA_4580.BR_NO;
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
