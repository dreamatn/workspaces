package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT2912 {
    int JIBS_tmp_int;
    DBParm BPTAPLI_RD;
    String JIBS_tmp_str[] = new String[10];
    String K_OUTPUT_FMT = "BP252";
    String CPN_S_BVAPP_MAINTAIN = "BP-S-BVAPP-MAINTAIN";
    String CPN_F_TLR_INF_QUERY = "BP-F-TLR-INF-QUERY";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCFTLRQ BPCFTLRQ = new BPCFTLRQ();
    BPCOAPLL BPCOAPLL = new BPCOAPLL();
    BPRAPLI BPRAPLI = new BPRAPLI();
    SCCGWA SCCGWA;
    BPB2910_AWA_2910 BPB2910_AWA_2910;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT2912 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB2910_AWA_2910>");
        BPB2910_AWA_2910 = (BPB2910_AWA_2910) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (SCCGWA.COMM_AREA.TR_BANK.equalsIgnoreCase("043")) {
            B010_CHECK_INPUT();
            B020_UPD_BV_APP();
        } else {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR35);
        }
        WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERROR;
        S000_ERR_MSG_PROC_LAST();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (BPB2910_AWA_2910.APP_NO == 0 
            || BPB2910_AWA_2910.APP_BR == 0) {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR33);
        }
        IBS.init(SCCGWA, BPRAPLI);
        BPRAPLI.KEY.APP_NO = BPB2910_AWA_2910.APP_NO;
        S000_READ_BPTAPLI();
        CEP.TRC(SCCGWA, BPRAPLI.APP_TYPE);
        IBS.init(SCCGWA, BPCFTLRQ);
        BPCFTLRQ.INFO.TLR = SCCGWA.COMM_AREA.TL_ID;
        S000_CALL_BPZFTLRQ();
        if (BPRAPLI.APP_TYPE == '0') {
            if (BPCFTLRQ.INFO.TLR_STSW == null) BPCFTLRQ.INFO.TLR_STSW = "";
            JIBS_tmp_int = BPCFTLRQ.INFO.TLR_STSW.length();
            for (int i=0;i<64-JIBS_tmp_int;i++) BPCFTLRQ.INFO.TLR_STSW += " ";
            if (!BPCFTLRQ.INFO.TLR_STSW.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("1")) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NOT_VLT_TLR;
                S000_ERR_MSG_PROC();
            }
        }
        if (!SCCGWA.COMM_AREA.TL_ID.equalsIgnoreCase(BPRAPLI.APP_TLR)) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.A_NOT_SAME_TLR;
            S000_ERR_MSG_PROC();
        }
        if (BPCFTLRQ.INFO.NEW_BR != BPB2910_AWA_2910.APP_BR) {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR34);
        }
    }
    public void S000_READ_BPTAPLI() throws IOException,SQLException,Exception {
        BPTAPLI_RD = new DBParm();
        BPTAPLI_RD.TableName = "BPTAPLI";
        BPTAPLI_RD.where = "APP_NO = :BPRAPLI.KEY.APP_NO";
        BPTAPLI_RD.upd = true;
        IBS.READ(SCCGWA, BPRAPLI, this, BPTAPLI_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_APLI_NOTFND);
        }
    }
    public void B020_UPD_BV_APP() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCOAPLL);
        BPCOAPLL.FUNC = 'M';
        BPCOAPLL.OUTPUT_FMT = K_OUTPUT_FMT;
        BPCOAPLL.APP_NO = BPB2910_AWA_2910.APP_NO;
        BPCOAPLL.MODIFY_STS = BPB2910_AWA_2910.FLG;
        S000_CALL_BPZSAPLL();
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
