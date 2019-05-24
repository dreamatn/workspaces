package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZSLUPD {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    SCZEXIT_WS_STAR_COMM WS_STAR_COMM;
    String CPN_BPZSBSP = "BP-S-LONG-BSP";
    String WS_ERR_MSG = " ";
    int WS_I = 0;
    int WS_IP_LEN_FULL = 0;
    int WS_IP_LEN = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCSBSP BPCSBSP = new BPCSBSP();
    SCCSTAR SCCSTAR = new SCCSTAR();
    SCCGWA SCCGWA;
    BPCSLUPD BPCSLUPD;
    public void MP(SCCGWA SCCGWA, BPCSLUPD BPCSLUPD) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCSLUPD = BPCSLUPD;
        CEP.TRC(SCCGWA);
        A00_INIT_PROC();
        B00_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPZSLUPD return!");
        Z_RET();
    }
    public void A00_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSLUPD.RC);
        IBS.init(SCCGWA, BPCSBSP);
    }
    public void B00_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_START_BSP();
    }
    public void B100_START_BSP() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "XXXXXXXXXXXXXXXXXXXXXXXXXXX");
        CEP.TRC(SCCGWA, BPCSLUPD);
        BPCSBSP.AP_PROC = BPCSLUPD.PROC_NAME;
        if (BPCSBSP.PROC_DATA1 == null) BPCSBSP.PROC_DATA1 = "";
        JIBS_tmp_int = BPCSBSP.PROC_DATA1.length();
        for (int i=0;i<60-JIBS_tmp_int;i++) BPCSBSP.PROC_DATA1 += " ";
        BPCSBSP.PROC_DATA1 = "BTNO=" + BPCSBSP.PROC_DATA1.substring(5);
        IBS.CPY2CLS(SCCGWA, BPCSBSP.PROC_DATA1, BPCSBSP.BATNO);
        if (BPCSBSP.PROC_DATA1 == null) BPCSBSP.PROC_DATA1 = "";
        JIBS_tmp_int = BPCSBSP.PROC_DATA1.length();
        for (int i=0;i<60-JIBS_tmp_int;i++) BPCSBSP.PROC_DATA1 += " ";
        if (BPCSLUPD.PARM_DATA.BATCH_NO == null) BPCSLUPD.PARM_DATA.BATCH_NO = "";
        JIBS_tmp_int = BPCSLUPD.PARM_DATA.BATCH_NO.length();
        for (int i=0;i<23-JIBS_tmp_int;i++) BPCSLUPD.PARM_DATA.BATCH_NO += " ";
        BPCSBSP.PROC_DATA1 = BPCSBSP.PROC_DATA1.substring(0, 6 - 1) + BPCSLUPD.PARM_DATA.BATCH_NO + BPCSBSP.PROC_DATA1.substring(6 + 36 - 1);
        IBS.CPY2CLS(SCCGWA, BPCSBSP.PROC_DATA1, BPCSBSP.BATNO);
        if (BPCSBSP.PROC_DATA1 == null) BPCSBSP.PROC_DATA1 = "";
        JIBS_tmp_int = BPCSBSP.PROC_DATA1.length();
        for (int i=0;i<60-JIBS_tmp_int;i++) BPCSBSP.PROC_DATA1 += " ";
        BPCSBSP.PROC_DATA1 = BPCSBSP.PROC_DATA1.substring(0, 42 - 1) + "," + BPCSBSP.PROC_DATA1.substring(42 + 1 - 1);
        IBS.CPY2CLS(SCCGWA, BPCSBSP.PROC_DATA1, BPCSBSP.BATNO);
        if (BPCSBSP.PROC_DATA1 == null) BPCSBSP.PROC_DATA1 = "";
        JIBS_tmp_int = BPCSBSP.PROC_DATA1.length();
        for (int i=0;i<60-JIBS_tmp_int;i++) BPCSBSP.PROC_DATA1 += " ";
        BPCSBSP.PROC_DATA1 = BPCSBSP.PROC_DATA1.substring(0, 43 - 1) + "TYP=" + BPCSBSP.PROC_DATA1.substring(43 + 4 - 1);
        IBS.CPY2CLS(SCCGWA, BPCSBSP.PROC_DATA1, BPCSBSP.BATNO);
        if (BPCSBSP.PROC_DATA1 == null) BPCSBSP.PROC_DATA1 = "";
        JIBS_tmp_int = BPCSBSP.PROC_DATA1.length();
        for (int i=0;i<60-JIBS_tmp_int;i++) BPCSBSP.PROC_DATA1 += " ";
        if (BPCSLUPD.PARM_DATA.UP_TYPE == null) BPCSLUPD.PARM_DATA.UP_TYPE = "";
        JIBS_tmp_int = BPCSLUPD.PARM_DATA.UP_TYPE.length();
        for (int i=0;i<2-JIBS_tmp_int;i++) BPCSLUPD.PARM_DATA.UP_TYPE += " ";
        BPCSBSP.PROC_DATA1 = BPCSBSP.PROC_DATA1.substring(0, 47 - 1) + BPCSLUPD.PARM_DATA.UP_TYPE + BPCSBSP.PROC_DATA1.substring(47 + 2 - 1);
        IBS.CPY2CLS(SCCGWA, BPCSBSP.PROC_DATA1, BPCSBSP.BATNO);
        if (BPCSBSP.PROC_DATA1 == null) BPCSBSP.PROC_DATA1 = "";
        JIBS_tmp_int = BPCSBSP.PROC_DATA1.length();
        for (int i=0;i<60-JIBS_tmp_int;i++) BPCSBSP.PROC_DATA1 += " ";
        BPCSBSP.PROC_DATA1 = BPCSBSP.PROC_DATA1.substring(0, 49 - 1) + "," + BPCSBSP.PROC_DATA1.substring(49 + 1 - 1);
        IBS.CPY2CLS(SCCGWA, BPCSBSP.PROC_DATA1, BPCSBSP.BATNO);
        CEP.TRC(SCCGWA, BPCSBSP.PROC_DATA1);
        if (BPCSBSP.PROC_DATA2 == null) BPCSBSP.PROC_DATA2 = "";
        JIBS_tmp_int = BPCSBSP.PROC_DATA2.length();
        for (int i=0;i<60-JIBS_tmp_int;i++) BPCSBSP.PROC_DATA2 += " ";
        BPCSBSP.PROC_DATA2 = "TRM=" + BPCSBSP.PROC_DATA2.substring(4);
        if (BPCSBSP.PROC_DATA2 == null) BPCSBSP.PROC_DATA2 = "";
        JIBS_tmp_int = BPCSBSP.PROC_DATA2.length();
        for (int i=0;i<60-JIBS_tmp_int;i++) BPCSBSP.PROC_DATA2 += " ";
        BPCSBSP.PROC_DATA2 = BPCSBSP.PROC_DATA2.substring(0, 5 - 1) + "T" + BPCSBSP.PROC_DATA2.substring(5 + 1 - 1);
        if (BPCSLUPD.PARM_DATA.TLR_NO == null) BPCSLUPD.PARM_DATA.TLR_NO = "";
        JIBS_tmp_int = BPCSLUPD.PARM_DATA.TLR_NO.length();
        for (int i=0;i<8-JIBS_tmp_int;i++) BPCSLUPD.PARM_DATA.TLR_NO += " ";
        if (BPCSBSP.PROC_DATA2 == null) BPCSBSP.PROC_DATA2 = "";
        JIBS_tmp_int = BPCSBSP.PROC_DATA2.length();
        for (int i=0;i<60-JIBS_tmp_int;i++) BPCSBSP.PROC_DATA2 += " ";
        BPCSBSP.PROC_DATA2 = BPCSBSP.PROC_DATA2.substring(0, 6 - 1) + BPCSLUPD.PARM_DATA.TLR_NO.substring(0, 7) + BPCSBSP.PROC_DATA2.substring(6 + 7 - 1);
        if (BPCSBSP.PROC_DATA2 == null) BPCSBSP.PROC_DATA2 = "";
        JIBS_tmp_int = BPCSBSP.PROC_DATA2.length();
        for (int i=0;i<60-JIBS_tmp_int;i++) BPCSBSP.PROC_DATA2 += " ";
        BPCSBSP.PROC_DATA2 = BPCSBSP.PROC_DATA2.substring(0, 13 - 1) + "," + BPCSBSP.PROC_DATA2.substring(13 + 1 - 1);
        if (BPCSBSP.PROC_DATA2 == null) BPCSBSP.PROC_DATA2 = "";
        JIBS_tmp_int = BPCSBSP.PROC_DATA2.length();
        for (int i=0;i<60-JIBS_tmp_int;i++) BPCSBSP.PROC_DATA2 += " ";
        BPCSBSP.PROC_DATA2 = BPCSBSP.PROC_DATA2.substring(0, 14 - 1) + "NAM=" + BPCSBSP.PROC_DATA2.substring(14 + 4 - 1);
        if (BPCSBSP.PROC_DATA2 == null) BPCSBSP.PROC_DATA2 = "";
        JIBS_tmp_int = BPCSBSP.PROC_DATA2.length();
        for (int i=0;i<60-JIBS_tmp_int;i++) BPCSBSP.PROC_DATA2 += " ";
        if (BPCSLUPD.PARM_DATA.FILE_NAME.FIL_NAM == null) BPCSLUPD.PARM_DATA.FILE_NAME.FIL_NAM = "";
        JIBS_tmp_int = BPCSLUPD.PARM_DATA.FILE_NAME.FIL_NAM.length();
        for (int i=0;i<8-JIBS_tmp_int;i++) BPCSLUPD.PARM_DATA.FILE_NAME.FIL_NAM += " ";
        BPCSBSP.PROC_DATA2 = BPCSBSP.PROC_DATA2.substring(0, 18 - 1) + BPCSLUPD.PARM_DATA.FILE_NAME.FIL_NAM + BPCSBSP.PROC_DATA2.substring(18 + 8 - 1);
        if (BPCSBSP.PROC_DATA2 == null) BPCSBSP.PROC_DATA2 = "";
        JIBS_tmp_int = BPCSBSP.PROC_DATA2.length();
        for (int i=0;i<60-JIBS_tmp_int;i++) BPCSBSP.PROC_DATA2 += " ";
        BPCSBSP.PROC_DATA2 = BPCSBSP.PROC_DATA2.substring(0, 26 - 1) + "," + BPCSBSP.PROC_DATA2.substring(26 + 1 - 1);
        if (BPCSBSP.PROC_DATA2 == null) BPCSBSP.PROC_DATA2 = "";
        JIBS_tmp_int = BPCSBSP.PROC_DATA2.length();
        for (int i=0;i<60-JIBS_tmp_int;i++) BPCSBSP.PROC_DATA2 += " ";
        BPCSBSP.PROC_DATA2 = BPCSBSP.PROC_DATA2.substring(0, 27 - 1) + "IP=" + BPCSBSP.PROC_DATA2.substring(27 + 3 - 1);
        WS_IP_LEN_FULL = 15;
        WS_IP_LEN = 15;
        for (WS_I = WS_IP_LEN_FULL; WS_I >= 2; WS_I += -1) {
            if (BPCSLUPD.PARM_DATA.IP_ADDR == null) BPCSLUPD.PARM_DATA.IP_ADDR = "";
            JIBS_tmp_int = BPCSLUPD.PARM_DATA.IP_ADDR.length();
            for (int i=0;i<15-JIBS_tmp_int;i++) BPCSLUPD.PARM_DATA.IP_ADDR += " ";
            if (BPCSLUPD.PARM_DATA.IP_ADDR.substring(WS_I - 1, WS_I + 1 - 1).trim().length() > 0) {
                WS_IP_LEN = WS_I;
                WS_I = 1;
            }
        }
        if (BPCSBSP.PROC_DATA2 == null) BPCSBSP.PROC_DATA2 = "";
        JIBS_tmp_int = BPCSBSP.PROC_DATA2.length();
        for (int i=0;i<60-JIBS_tmp_int;i++) BPCSBSP.PROC_DATA2 += " ";
        if (BPCSLUPD.PARM_DATA.IP_ADDR == null) BPCSLUPD.PARM_DATA.IP_ADDR = "";
        JIBS_tmp_int = BPCSLUPD.PARM_DATA.IP_ADDR.length();
        for (int i=0;i<15-JIBS_tmp_int;i++) BPCSLUPD.PARM_DATA.IP_ADDR += " ";
        BPCSBSP.PROC_DATA2 = BPCSBSP.PROC_DATA2.substring(0, 30 - 1) + BPCSLUPD.PARM_DATA.IP_ADDR + BPCSBSP.PROC_DATA2.substring(30 + WS_IP_LEN - 1);
        if (BPCSBSP.PROC_DATA2 == null) BPCSBSP.PROC_DATA2 = "";
        JIBS_tmp_int = BPCSBSP.PROC_DATA2.length();
        for (int i=0;i<60-JIBS_tmp_int;i++) BPCSBSP.PROC_DATA2 += " ";
        BPCSBSP.PROC_DATA2 = BPCSBSP.PROC_DATA2.substring(0, 30 + WS_IP_LEN - 1) + "," + BPCSBSP.PROC_DATA2.substring(30 + WS_IP_LEN + 1 - 1);
        CEP.TRC(SCCGWA, BPCSBSP.PROC_DATA2);
        BPCSBSP.PROC_DATA3 = " ";
        if (BPCSBSP.PROC_DATA3 == null) BPCSBSP.PROC_DATA3 = "";
        JIBS_tmp_int = BPCSBSP.PROC_DATA3.length();
        for (int i=0;i<60-JIBS_tmp_int;i++) BPCSBSP.PROC_DATA3 += " ";
        BPCSBSP.PROC_DATA3 = "TLR=" + BPCSBSP.PROC_DATA3.substring(4);
        if (BPCSBSP.PROC_DATA3 == null) BPCSBSP.PROC_DATA3 = "";
        JIBS_tmp_int = BPCSBSP.PROC_DATA3.length();
        for (int i=0;i<60-JIBS_tmp_int;i++) BPCSBSP.PROC_DATA3 += " ";
        if (BPCSLUPD.PARM_DATA.TLR_NO == null) BPCSLUPD.PARM_DATA.TLR_NO = "";
        JIBS_tmp_int = BPCSLUPD.PARM_DATA.TLR_NO.length();
        for (int i=0;i<8-JIBS_tmp_int;i++) BPCSLUPD.PARM_DATA.TLR_NO += " ";
        BPCSBSP.PROC_DATA3 = BPCSBSP.PROC_DATA3.substring(0, 5 - 1) + BPCSLUPD.PARM_DATA.TLR_NO + BPCSBSP.PROC_DATA3.substring(5 + 20 - 1);
        CEP.TRC(SCCGWA, BPCSBSP.PROC_DATA3);
        if (BPCSBSP.PROC_DATA3 == null) BPCSBSP.PROC_DATA3 = "";
        JIBS_tmp_int = BPCSBSP.PROC_DATA3.length();
        for (int i=0;i<60-JIBS_tmp_int;i++) BPCSBSP.PROC_DATA3 += " ";
        BPCSBSP.PROC_DATA3 = BPCSBSP.PROC_DATA3.substring(0, 25 - 1) + "," + BPCSBSP.PROC_DATA3.substring(25 + 1 - 1);
        if (BPCSBSP.PROC_DATA3 == null) BPCSBSP.PROC_DATA3 = "";
        JIBS_tmp_int = BPCSBSP.PROC_DATA3.length();
        for (int i=0;i<60-JIBS_tmp_int;i++) BPCSBSP.PROC_DATA3 += " ";
        BPCSBSP.PROC_DATA3 = BPCSBSP.PROC_DATA3.substring(0, 26 - 1) + "BH=" + BPCSBSP.PROC_DATA3.substring(26 + 3 - 1);
        if (BPCSBSP.PROC_DATA3 == null) BPCSBSP.PROC_DATA3 = "";
        JIBS_tmp_int = BPCSBSP.PROC_DATA3.length();
        for (int i=0;i<60-JIBS_tmp_int;i++) BPCSBSP.PROC_DATA3 += " ";
        JIBS_tmp_str[0] = "" + BPCSLUPD.PARM_DATA.BR_NO;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<6-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        BPCSBSP.PROC_DATA3 = BPCSBSP.PROC_DATA3.substring(0, 29 - 1) + JIBS_tmp_str[0] + BPCSBSP.PROC_DATA3.substring(29 + 11 - 1);
        CEP.TRC(SCCGWA, BPCSBSP.PROC_DATA3);
        S000_CALL_BPZSBSP();
    }
    public void S000_CALL_BPZSBSP() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "START BPZSBSP");
        WS_STAR_COMM = new SCZEXIT_WS_STAR_COMM();
        WS_STAR_COMM.STAR_PGM = "BPZSBSP";
        WS_STAR_COMM.STAR_DATA = BPCSBSP;
        IBS.START(SCCGWA, WS_STAR_COMM, true);
        CEP.TRC(SCCGWA, "END   BPZSBSP");
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
