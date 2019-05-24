package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT3920 {
    String JIBS_tmp_str[] = new String[10];
    char K_ERROR = 'E';
    String K_OUTPUT_FMT = "BP166";
    String CPN_S_MGM_BACT = "BP-S-MGM-BACT ";
    String CPN_P_INQ_ORG = "BP-P-INQ-ORG        ";
    String BP_F_BV_PRM_QUERY = "BP-F-BV-PRM-QUERY   ";
    String CPN_ITM_INQ = "BP-P-CHK-ACCT-CODE";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCSBACT BPCSBACT = new BPCSBACT();
    BPCFBVQU BPCFBVQU = new BPCFBVQU();
    BPCPQGLM BPCPQGLM = new BPCPQGLM();
    SCCGWA SCCGWA;
    BPB3920_AWA_3920 BPB3920_AWA_3920;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT3920 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB3920_AWA_3920>");
        BPB3920_AWA_3920 = (BPB3920_AWA_3920) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, BPCSBACT);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_ADD_CDTL_RECORD();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFBVQU);
        BPCFBVQU.TX_DATA.KEY.CODE = BPB3920_AWA_3920.CODE;
        S000_CALL_BPZFBVQU();
        IBS.init(SCCGWA, BPCPQGLM);
        BPCPQGLM.DAT.INPUT.MSTNO = BPB3920_AWA_3920.ACC_CD;
        S000_CALL_BPZPQGLM();
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPQGLM.RC);
        if (!JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_NORMAL)) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQGLM.RC);
            WS_FLD_NO = BPB3920_AWA_3920.ACC_CD_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (BPB3920_AWA_3920.EFF_DATE >= BPB3920_AWA_3920.EXP_DATE) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_DATE_RANGE_ERR;
            WS_FLD_NO = BPB3920_AWA_3920.EFF_DATE_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (BPB3920_AWA_3920.EFF_DATE < SCCGWA.COMM_AREA.AC_DATE) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_LESS_ACDATE;
            WS_FLD_NO = BPB3920_AWA_3920.EFF_DATE_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (BPB3920_AWA_3920.EXP_DATE < SCCGWA.COMM_AREA.AC_DATE) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_LESS_ACDATE;
            WS_FLD_NO = BPB3920_AWA_3920.EXP_DATE_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
    }
    public void B020_ADD_CDTL_RECORD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSBACT);
        BPCSBACT.FUNC = 'A';
        BPCSBACT.OUTPUT_FMT = K_OUTPUT_FMT;
        CEP.TRC(SCCGWA, BPB3920_AWA_3920.DESC);
        CEP.TRC(SCCGWA, BPB3920_AWA_3920.CDSC);
        CEP.TRC(SCCGWA, BPB3920_AWA_3920.EFF_DATE);
        CEP.TRC(SCCGWA, BPB3920_AWA_3920.EXP_DATE);
        R000_TRANS_DATA_PARAMETER();
        S000_CALL_BPZSBACT();
    }
    public void R000_TRANS_DATA_PARAMETER() throws IOException,SQLException,Exception {
        BPCSBACT.FUNC = BPB3920_AWA_3920.FUNC;
        BPCSBACT.CODE = BPB3920_AWA_3920.CODE;
        BPCSBACT.STAT = BPB3920_AWA_3920.STAT;
        BPCSBACT.BV_KIND = BPB3920_AWA_3920.BV_KIND;
        BPCSBACT.ACC_CD = BPB3920_AWA_3920.ACC_CD;
        BPCSBACT.SUP_FLG = BPB3920_AWA_3920.SUP_FLG;
        BPCSBACT.DESC = BPB3920_AWA_3920.DESC;
        BPCSBACT.CDSC = BPB3920_AWA_3920.CDSC;
        BPCSBACT.EFF_DATE = BPB3920_AWA_3920.EFF_DATE;
        BPCSBACT.EXP_DATE = BPB3920_AWA_3920.EXP_DATE;
    }
    public void S000_CALL_BPZFBVQU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, BP_F_BV_PRM_QUERY, BPCFBVQU);
        if (BPCFBVQU.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCFBVQU.RC);
            if (BPB3920_AWA_3920.CODE.trim().length() == 0) WS_FLD_NO = 0;
            else WS_FLD_NO = Short.parseShort(BPB3920_AWA_3920.CODE);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZPQGLM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-GLMT", BPCPQGLM);
    }
    public void S000_CALL_BPZSBACT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_S_MGM_BACT, BPCSBACT);
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
