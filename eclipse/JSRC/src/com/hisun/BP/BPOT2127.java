package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT2127 {
    String JIBS_tmp_str[] = new String[10];
    DBParm BPTALIB_RD;
    String K_OUTPUT_FMT = "BP122";
    String CPN_S_CSHAPP_MAINTAIN = "BP-S-CSHAPP-MAINTAIN";
    String CPN_F_TLR_INF_QUERY = "BP-F-TLR-INF-QUERY";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    BPOT2127_WS_APP_INFO WS_APP_INFO = new BPOT2127_WS_APP_INFO();
    int WS_APP_NO = 0;
    int WS_CNT = 0;
    int WS_CNT1 = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCFTLRQ BPCFTLRQ = new BPCFTLRQ();
    BPCOLIBB BPCOLIBB = new BPCOLIBB();
    BPRALIB BPRALIB = new BPRALIB();
    SCCGWA SCCGWA;
    BPB2122_AWA_2122 BPB2122_AWA_2122;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_APP_INFO.WS_APP_G[WS_CNT1-1]);
        for (WS_CNT1 = 1; WS_CNT1 <= 2 
            && JIBS_tmp_str[0].trim().length() != 0; WS_CNT1 += 1) {
            CEP.TRC(SCCGWA, WS_CNT1);
            CEP.TRC(SCCGWA, WS_APP_INFO.WS_APP_G[WS_CNT1-1]);
        }
        CEP.TRC(SCCGWA, WS_CNT1);
        if (WS_CNT1 == 1) {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR142);
        }
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_APP_INFO.WS_APP_G[WS_CNT-1]);
        for (WS_CNT = 1; WS_CNT <= 200 
            && (JIBS_tmp_str[0].trim().length() != 0 
            || WS_CNT <= 1); WS_CNT += 1) {
            CEP.TRC(SCCGWA, WS_CNT);
            if (WS_CNT1 > 2) {
                A000_REDF_AWA();
            }
            WS_APP_NO = WS_APP_INFO.WS_APP_G[WS_CNT-1].WS_APP_N;
            CEP.TRC(SCCGWA, WS_CNT);
            CEP.TRC(SCCGWA, WS_APP_INFO.WS_APP_G[WS_CNT-1].WS_APP_N);
            CEP.TRC(SCCGWA, WS_APP_NO);
            B000_MAIN_PROC();
            IBS.CPY2CLS(SCCGWA, BPB2122_AWA_2122.APP_G, WS_APP_INFO);
        }
        CEP.TRC(SCCGWA, "BPOT2127 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB2122_AWA_2122>");
        BPB2122_AWA_2122 = (BPB2122_AWA_2122) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.CPY2CLS(SCCGWA, BPB2122_AWA_2122.APP_G, WS_APP_INFO);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (SCCGWA.COMM_AREA.TR_BANK.equalsIgnoreCase("043")) {
            B010_CHECK_INPUT();
            B020_UPD_CASH_APP();
        } else {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR35);
        }
        WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERROR;
        S000_ERR_MSG_PROC_LAST();
    }
    public void A000_REDF_AWA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRALIB);
        CEP.TRC(SCCGWA, WS_CNT);
        CEP.TRC(SCCGWA, WS_APP_INFO.WS_APP_G[WS_CNT-1].WS_APP_N);
        BPRALIB.KEY.APP_NO = WS_APP_INFO.WS_APP_G[WS_CNT-1].WS_APP_N;
        CEP.TRC(SCCGWA, WS_CNT);
        CEP.TRC(SCCGWA, BPRALIB.KEY.APP_NO);
        T000_READ_BPTALIB();
        BPB2122_AWA_2122.APP_BR = BPRALIB.APP_BR;
    }
    public void T000_READ_BPTALIB() throws IOException,SQLException,Exception {
        BPTALIB_RD = new DBParm();
        BPTALIB_RD.TableName = "BPTALIB";
        BPTALIB_RD.where = "APP_NO = :BPRALIB.KEY.APP_NO";
        BPTALIB_RD.upd = true;
        IBS.READ(SCCGWA, BPRALIB, this, BPTALIB_RD);
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_APP_NO);
        CEP.TRC(SCCGWA, BPB2122_AWA_2122.APP_BR);
        CEP.TRC(SCCGWA, BPB2122_AWA_2122.FLG);
        if (BPB2122_AWA_2122.APP_BR == 0) {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR33);
        }
        IBS.init(SCCGWA, BPCFTLRQ);
        BPCFTLRQ.INFO.TLR = SCCGWA.COMM_AREA.TL_ID;
        S000_CALL_BPZFTLRQ();
        if (BPCFTLRQ.INFO.NEW_BR == BPB2122_AWA_2122.APP_BR) {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR34);
        }
    }
    public void B020_UPD_CASH_APP() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCOLIBB);
        BPCOLIBB.FUNC = 'M';
        BPCOLIBB.OUTPUT_FMT = K_OUTPUT_FMT;
        BPCOLIBB.APP_NO = WS_APP_NO;
        BPCOLIBB.CNT = WS_CNT1;
        CEP.TRC(SCCGWA, WS_CNT1);
        BPCOLIBB.MODIFY_STS = BPB2122_AWA_2122.FLG;
        S000_CALL_BPZSLIBB();
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
