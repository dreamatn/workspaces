package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT2913 {
    String JIBS_tmp_str[] = new String[10];
    int JIBS_tmp_int;
    DBParm BPTAPLI_RD;
    String K_OUTPUT_FMT = "BP253";
    String CPN_S_BVAPP_MAINTAIN = "BP-S-BVAPP-MAINTAIN";
    String CPN_F_TLR_INF_QUERY = "BP-F-TLR-INF-QUERY";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    int WS_CNT = 0;
    int WS_I = 0;
    BPOT2913_WS_APP_INFO WS_APP_INFO = new BPOT2913_WS_APP_INFO();
    int WS_APP_NO = 0;
    int WS_A = 0;
    int WS_COUNT = 0;
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
        WS_COUNT = 0;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_APP_INFO.WS_APP_G[WS_CNT-1]);
        for (WS_CNT = 1; WS_CNT <= 2 
            && JIBS_tmp_str[0].trim().length() != 0; WS_CNT += 1) {
            WS_COUNT = WS_COUNT + 1;
        }
        CEP.TRC(SCCGWA, WS_CNT);
        if (WS_CNT == 1) {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR142);
        }
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_APP_INFO.WS_APP_G[WS_A-1]);
        for (WS_A = 1; WS_A <= 200 
            && JIBS_tmp_str[0].trim().length() != 0; WS_A += 1) {
            WS_APP_NO = 0;
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_APP_INFO.WS_APP_G[WS_A-1]);
            WS_APP_NO = Integer.parseInt(JIBS_tmp_str[0]);
            CEP.TRC(SCCGWA, WS_A);
            CEP.TRC(SCCGWA, WS_APP_INFO.WS_APP_G[WS_A-1]);
            B000_MAIN_PROC();
            IBS.CPY2CLS(SCCGWA, BPB2910_AWA_2910.APP_G, WS_APP_INFO);
        }
        CEP.TRC(SCCGWA, "BPOT2913 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB2910_AWA_2910>");
        BPB2910_AWA_2910 = (BPB2910_AWA_2910) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.CPY2CLS(SCCGWA, BPB2910_AWA_2910.APP_G, WS_APP_INFO);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_UPD_BV_APP();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRAPLI);
        BPRAPLI.KEY.APP_NO = WS_APP_NO;
        CEP.TRC(SCCGWA, BPRAPLI.KEY.APP_NO);
        T000_READ_BPTAPLI();
        CEP.TRC(SCCGWA, BPRAPLI.APP_BR);
        BPB2910_AWA_2910.APP_BR = BPRAPLI.APP_BR;
        BPB2910_AWA_2910.APP_NO = BPRAPLI.KEY.APP_NO;
        if (BPB2910_AWA_2910.APP_NO == 0 
            || BPB2910_AWA_2910.APP_BR == 0) {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR33);
        }
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
        if (BPCFTLRQ.INFO.NEW_BR == BPB2910_AWA_2910.APP_BR) {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR34);
        }
    }
    public void B020_UPD_BV_APP() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCOAPLL);
        BPCOAPLL.FUNC = 'M';
        BPCOAPLL.OUTPUT_FMT = K_OUTPUT_FMT;
        BPCOAPLL.APP_NO = WS_APP_NO;
        CEP.TRC(SCCGWA, WS_COUNT);
        BPCOAPLL.CNT = WS_COUNT;
        BPCOAPLL.MODIFY_STS = BPB2910_AWA_2910.FLG;
        S000_CALL_BPZSAPLL();
    }
    public void T000_READ_BPTAPLI() throws IOException,SQLException,Exception {
        BPTAPLI_RD = new DBParm();
        BPTAPLI_RD.TableName = "BPTAPLI";
        BPTAPLI_RD.where = "APP_NO = :BPRAPLI.KEY.APP_NO";
        IBS.READ(SCCGWA, BPRAPLI, this, BPTAPLI_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_APLI_NOTFND);
        }
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
