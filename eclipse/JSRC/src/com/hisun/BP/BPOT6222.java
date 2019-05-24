package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT6222 {
    String JIBS_tmp_str[] = new String[10];
    int JIBS_tmp_int;
    char K_ERROR = 'E';
    String CPN_SCSSCKDT = "SCSSCKDT";
    String CPN_MAINTAIN_THOL = "BP-S-MAINTAIN-THOL";
    String CPN_BP_I_INQ_CITY = "BP-I-INQ-CITY     ";
    String CPN_BP_I_INQ_CNTY = "BP-I-INQ-CNTY";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    int WS_I = 0;
    int WS_J = 0;
    int WS_MAX_HOL_CNT = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCSTHOL BPCSTHOL = new BPCSTHOL();
    SCCCKDT SCCCKDT = new SCCCKDT();
    BPCIQCIT BPCIQCIT = new BPCIQCIT();
    BPCIQCNT BPCIQCNT = new BPCIQCNT();
    SCCGWA SCCGWA;
    BPB6220_AWA_6220 BPB6220_AWA_6220;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT6222 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB6220_AWA_6220>");
        BPB6220_AWA_6220 = (BPB6220_AWA_6220) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_UPDATE_HOL_PROCESS();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPB6220_AWA_6220.EFF_DT);
        if (BPB6220_AWA_6220.EFF_DT != 0) {
            IBS.init(SCCGWA, SCCCKDT);
            SCCCKDT.DATE = BPB6220_AWA_6220.EFF_DT;
            SCSSCKDT SCSSCKDT1 = new SCSSCKDT();
            SCSSCKDT1.MP(SCCGWA, SCCCKDT);
            CEP.TRC(SCCGWA, SCCCKDT.RC);
            if (SCCCKDT.RC != 0) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_EFF_DT_INVALID;
                WS_FLD_NO = BPB6220_AWA_6220.EFF_DT_NO;
                S000_ERR_MSG_PROC();
            }
        }
        for (WS_J = 1; WS_J <= 50; WS_J += 1) {
            CEP.TRC(SCCGWA, BPB6220_AWA_6220.HOL_DATA[WS_J-1].HOL_DT);
            CEP.TRC(SCCGWA, BPB6220_AWA_6220.HOL_DATA[WS_J-1].HOL_DT);
            JIBS_tmp_str[0] = "" + BPB6220_AWA_6220.HOL_DATA[WS_J-1].HOL_DT;
            JIBS_tmp_int = JIBS_tmp_str[0].length();
            for (int i=0;i<4-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
            CEP.TRC(SCCGWA, JIBS_tmp_str[0].substring(0, 2));
            CEP.TRC(SCCGWA, BPB6220_AWA_6220.HOL_DATA[WS_J-1].HOL_DT);
            JIBS_tmp_str[0] = "" + BPB6220_AWA_6220.HOL_DATA[WS_J-1].HOL_DT;
            JIBS_tmp_int = JIBS_tmp_str[0].length();
            for (int i=0;i<4-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
            CEP.TRC(SCCGWA, JIBS_tmp_str[0].substring(3 - 1, 3 + 2 - 1));
            if (BPB6220_AWA_6220.HOL_DATA[WS_J-1].HOL_DT != 0) {
                JIBS_tmp_str[0] = "" + BPB6220_AWA_6220.HOL_DATA[WS_J-1].HOL_DT;
                JIBS_tmp_int = JIBS_tmp_str[0].length();
                for (int i=0;i<4-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
                JIBS_tmp_str[1] = "" + BPB6220_AWA_6220.HOL_DATA[WS_J-1].HOL_DT;
                JIBS_tmp_int = JIBS_tmp_str[1].length();
                for (int i=0;i<4-JIBS_tmp_int;i++) JIBS_tmp_str[1] = "0"+JIBS_tmp_str[1];
                JIBS_tmp_str[2] = "" + BPB6220_AWA_6220.HOL_DATA[WS_J-1].HOL_DT;
                JIBS_tmp_int = JIBS_tmp_str[2].length();
                for (int i=0;i<4-JIBS_tmp_int;i++) JIBS_tmp_str[2] = "0"+JIBS_tmp_str[2];
                JIBS_tmp_str[3] = "" + BPB6220_AWA_6220.HOL_DATA[WS_J-1].HOL_DT;
                JIBS_tmp_int = JIBS_tmp_str[3].length();
                for (int i=0;i<4-JIBS_tmp_int;i++) JIBS_tmp_str[3] = "0"+JIBS_tmp_str[3];
                if (JIBS_tmp_str[0].substring(0, 2).compareTo("12") > 0 
                    || JIBS_tmp_str[1].substring(0, 2).compareTo("1") < 0 
                    || JIBS_tmp_str[2].substring(3 - 1, 3 + 2 - 1).compareTo("1") < 0 
                    || JIBS_tmp_str[3].substring(3 - 1, 3 + 2 - 1).compareTo("31") > 0) {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_DAY_NO_ERROR;
                    WS_FLD_NO = BPB6220_AWA_6220.HOL_DATA[WS_J-1].HOL_DT_NO;
                    S000_ERR_MSG_PROC();
                }
                WS_MAX_HOL_CNT += BPB6220_AWA_6220.HOL_DATA[WS_J-1].HOL_CNT;
                if (WS_MAX_HOL_CNT > 50) {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_HOL_CNT_TOO_MAX;
                    WS_FLD_NO = BPB6220_AWA_6220.HOL_DATA[WS_J-1].HOL_CNT_NO;
                    S000_ERR_MSG_PROC();
                }
            }
            if ((BPB6220_AWA_6220.HOL_DATA[WS_J-1].HOL_CNT == 0 
                && BPB6220_AWA_6220.HOL_DATA[WS_J-1].HOL_DT != 0) 
                || (BPB6220_AWA_6220.HOL_DATA[WS_J-1].HOL_CNT != 0 
                && BPB6220_AWA_6220.HOL_DATA[WS_J-1].HOL_DT == 0)) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_HOL_CNT_DT_PUT_ONE;
                S000_ERR_MSG_PROC();
            }
        }
    }
    public void B020_UPDATE_HOL_PROCESS() throws IOException,SQLException,Exception {
        WS_I = 0;
        IBS.init(SCCGWA, BPCSTHOL);
        BPCSTHOL.KEY.CAL_CD = BPB6220_AWA_6220.CAL_CD;
        BPCSTHOL.KEY.EFF_DT = BPB6220_AWA_6220.EFF_DT;
        BPCSTHOL.LAST_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCSTHOL.LAST_TELLER = SCCGWA.COMM_AREA.TL_ID;
        CEP.TRC(SCCGWA, BPCSTHOL.KEY.EFF_DT);
        for (WS_I = 1; WS_I <= 50; WS_I += 1) {
            BPCSTHOL.HOL_DATA[WS_I-1].HOL_DT = BPB6220_AWA_6220.HOL_DATA[WS_I-1].HOL_DT;
            CEP.TRC(SCCGWA, BPCSTHOL.HOL_DATA[WS_I-1].HOL_DT);
            BPCSTHOL.HOL_DATA[WS_I-1].HOL_CNT = BPB6220_AWA_6220.HOL_DATA[WS_I-1].HOL_CNT;
            BPCSTHOL.HOL_DATA[WS_I-1].HOL_NAME = BPB6220_AWA_6220.HOL_DATA[WS_I-1].HOL_NAME;
            BPCSTHOL.HOL_DATA[WS_I-1].HOL_TPY = '0';
        }
        if (BPB6220_AWA_6220.DATE != BPB6220_AWA_6220.EFF_DT) {
            BPCSTHOL.FUNC = 'M';
            BPCSTHOL.DATE = BPB6220_AWA_6220.DATE;
        } else {
            BPCSTHOL.FUNC = 'U';
        }
        CEP.TRC(SCCGWA, BPCSTHOL.KEY);
        CEP.TRC(SCCGWA, BPCSTHOL.FUNC);
        S000_CALL_BPZSTHOL();
    }
    public void S000_CALL_BPZSTHOL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_MAINTAIN_THOL, BPCSTHOL);
    }
    public void S000_CALL_BPZIQCIT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_BP_I_INQ_CITY, BPCIQCIT);
    }
    public void S000_CALL_BPZIQCNT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCIQCNT);
        IBS.CALLCPN(SCCGWA, CPN_BP_I_INQ_CNTY, BPCIQCNT);
        CEP.TRC(SCCGWA, BPCIQCNT.RC.RC_CODE);
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
