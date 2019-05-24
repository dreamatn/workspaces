package com.hisun.CI;

import com.hisun.SC.*;
import com.hisun.BP.*;

import java.util.Random;

import java.io.IOException;
import java.sql.SQLException;

public class CIZOPCJ {
    int JIBS_tmp_int;
    Random random;
    String JIBS_tmp_str[] = new String[10];
    int BAS_CI_NM_LEN;
    DBParm CITBAS_RD;
    DBParm CITJRL_RD;
    brParm CITJRL_BR = new brParm();
    short WS_I = 0;
    short WS_J = 0;
    short WS_CNT = 0;
    short WS_CNT1 = 0;
    short WS_COUNT = 0;
    int WS_RANDOM_NO = 0;
    String WS_CI_NO = " ";
    String WS_CHECK_CI_NO = " ";
    String WS_JCI_NO = " ";
    String WS_ID_NO = " ";
    String WS_FULL_NM = " ";
    char WS_JRL_FLG = ' ';
    CIZOPCJ_WS_CI_NM_DATA[] WS_CI_NM_DATA = new CIZOPCJ_WS_CI_NM_DATA[10];
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMSG SCCMSG = new SCCMSG();
    CIRBAS CIRBAS = new CIRBAS();
    CIRJRL CIRJRL = new CIRJRL();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    CICCINO CICCINO = new CICCINO();
    CICOSORC CICOSORC = new CICOSORC();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    CICOPCJ CICOPCJ;
    public CIZOPCJ() {
        for (int i=0;i<10;i++) WS_CI_NM_DATA[i] = new CIZOPCJ_WS_CI_NM_DATA();
    }
    public void MP(SCCGWA SCCGWA, CICOPCJ CICOPCJ) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.CICOPCJ = CICOPCJ;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "CIZOPCJ return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, CICOPCJ.RC);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        B020_GET_JRL_INFO();
        B030_GET_CI_NO();
        B040_GET_ID_NO();
        CEP.TRC(SCCGWA, WS_CNT);
        for (WS_I = 1; WS_I <= 10; WS_I += 1) {
            if (CICOPCJ.DATA[WS_I-1].CI_NO.trim().length() > 0) {
                B060_WRITE_CIRJRL_INFO();
            }
        }
        B070_WRITE_CIRBAS_INFO();
        B080_OUTPUT_CI_NO();
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        WS_CNT1 = 1;
        for (WS_I = 1; WS_I <= 10; WS_I += 1) {
            if (CICOPCJ.DATA[WS_I-1].CI_NO.trim().length() > 0) {
                WS_CNT += 1;
                CEP.TRC(SCCGWA, WS_CNT);
                IBS.init(SCCGWA, CIRBAS);
                CIRBAS.KEY.CI_NO = CICOPCJ.DATA[WS_I-1].CI_NO;
                T000_READ_CITBAS();
                if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
                    CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_IS_NOT_EXIST, "请先�?立该客户");
                }
                for (WS_J = 1; WS_J != WS_I; WS_J += 1) {
                    if (CICOPCJ.DATA[WS_I-1].CI_NO.equalsIgnoreCase(CICOPCJ.DATA[WS_J-1].CI_NO)) {
                        CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_INPUT_DATA_ERR, "不能输入相同的客户号");
                    }
                }
                if (CIRBAS.CI_TYP != '1') {
                    CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_INPUT_DATA_ERR, "联名客户户主必须为个人客�?");
                }
                if (WS_CNT > 1) {
                    if (WS_FULL_NM == null) WS_FULL_NM = "";
                    JIBS_tmp_int = WS_FULL_NM.length();
                    for (int i=0;i<252-JIBS_tmp_int;i++) WS_FULL_NM += " ";
                    WS_FULL_NM = WS_FULL_NM.substring(0, WS_CNT1 - 1) + "&" + WS_FULL_NM.substring(WS_CNT1 + 1 - 1);
                    WS_CNT1 += 1;
                }
                if (WS_FULL_NM == null) WS_FULL_NM = "";
                JIBS_tmp_int = WS_FULL_NM.length();
                for (int i=0;i<252-JIBS_tmp_int;i++) WS_FULL_NM += " ";
                if (CIRBAS.CI_NM == null) CIRBAS.CI_NM = "";
                JIBS_tmp_int = CIRBAS.CI_NM.length();
                for (int i=0;i<252-JIBS_tmp_int;i++) CIRBAS.CI_NM += " ";
                WS_FULL_NM = WS_FULL_NM.substring(0, WS_CNT1 - 1) + CIRBAS.CI_NM + WS_FULL_NM.substring(WS_CNT1 + CIRBAS.CI_NM.length() - 1);
                WS_CNT1 = (short) (CIRBAS.CI_NM.trim().length() + WS_CNT1);
            }
            if (CIRBAS.CI_ATTR != '1') {
                CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_ATTR_INPUT_ERR, "输入客户必须为正式客�?");
            }
            if (CIRBAS.STSW == null) CIRBAS.STSW = "";
            JIBS_tmp_int = CIRBAS.STSW.length();
            for (int i=0;i<80-JIBS_tmp_int;i++) CIRBAS.STSW += " ";
            if (CIRBAS.STSW.substring(3 - 1, 3 + 1 - 1).equalsIgnoreCase("1")) {
                CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_IS_CLOSE_STS);
            }
            if (CIRBAS.STSW == null) CIRBAS.STSW = "";
            JIBS_tmp_int = CIRBAS.STSW.length();
            for (int i=0;i<80-JIBS_tmp_int;i++) CIRBAS.STSW += " ";
            if (CIRBAS.STSW.substring(23 - 1, 23 + 1 - 1).equalsIgnoreCase("1")) {
                CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_STS_ABNORMAL, "该客户已被删除合�?");
            }
        }
        if (WS_CNT < 2) {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_JRL_NOT_LESS_TWO);
        }
    }
    public void B020_GET_JRL_INFO() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "GET JRL INFO");
        IBS.init(SCCGWA, CIRJRL);
        WS_I = 0;
        CIRJRL.KEY.HCI_NO = CICOPCJ.DATA[1-1].CI_NO;
        T000_STARTBR_CITJRL_BY_HCINO();
        T000_READNEXT_CITJRL();
        while (SCCGWA.COMM_AREA.DBIO_FLG != '1') {
            WS_COUNT = CIRJRL.NUM;
            WS_JCI_NO = CIRJRL.KEY.JCI_NO;
            WS_JRL_FLG = 'Y';
            if (WS_COUNT == WS_CNT) {
                for (WS_I = 2; WS_I <= WS_CNT 
                    && WS_JRL_FLG != 'N'; WS_I += 1) {
                    if (CICOPCJ.DATA[WS_I-1].CI_NO.trim().length() > 0) {
                        IBS.init(SCCGWA, CIRJRL);
                        CIRJRL.KEY.HCI_NO = CICOPCJ.DATA[WS_I-1].CI_NO;
                        CIRJRL.KEY.JCI_NO = WS_JCI_NO;
                        T000_READ_CITJRL();
                        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
                            WS_JRL_FLG = 'N';
                        }
                    }
                }
                if (WS_JRL_FLG == 'Y') {
                    CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_JRL_INF_EXIST);
                }
            }
            T000_READNEXT_CITJRL();
        }
        T000_ENDBR_CITJRL();
    }
    public void B030_GET_CI_NO() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "CALL CIZCINO");
        IBS.init(SCCGWA, CICCINO);
        S000_CALL_CIZCINO();
        WS_CI_NO = CICCINO.DATA.CI_NO;
        CEP.TRC(SCCGWA, WS_CI_NO);
    }
    public void B040_GET_ID_NO() throws IOException,SQLException,Exception {
        if (WS_CI_NO == null) WS_CI_NO = "";
        JIBS_tmp_int = WS_CI_NO.length();
        for (int i=0;i<12-JIBS_tmp_int;i++) WS_CI_NO += " ";
        if (WS_ID_NO == null) WS_ID_NO = "";
        JIBS_tmp_int = WS_ID_NO.length();
        for (int i=0;i<70-JIBS_tmp_int;i++) WS_ID_NO += " ";
        WS_ID_NO = WS_CI_NO.substring(10 - 1, 10 + 2 - 1) + WS_ID_NO.substring(2);
        if (WS_CI_NO == null) WS_CI_NO = "";
        JIBS_tmp_int = WS_CI_NO.length();
        for (int i=0;i<12-JIBS_tmp_int;i++) WS_CI_NO += " ";
        if (WS_ID_NO == null) WS_ID_NO = "";
        JIBS_tmp_int = WS_ID_NO.length();
        for (int i=0;i<70-JIBS_tmp_int;i++) WS_ID_NO += " ";
        WS_ID_NO = WS_ID_NO.substring(0, 3 - 1) + WS_CI_NO.substring(7 - 1, 7 + 3 - 1) + WS_ID_NO.substring(3 + 3 - 1);
        if (WS_CI_NO == null) WS_CI_NO = "";
        JIBS_tmp_int = WS_CI_NO.length();
        for (int i=0;i<12-JIBS_tmp_int;i++) WS_CI_NO += " ";
        if (WS_ID_NO == null) WS_ID_NO = "";
        JIBS_tmp_int = WS_ID_NO.length();
        for (int i=0;i<70-JIBS_tmp_int;i++) WS_ID_NO += " ";
        WS_ID_NO = WS_ID_NO.substring(0, 6 - 1) + WS_CI_NO.substring(0, 3) + WS_ID_NO.substring(6 + 3 - 1);
        if (WS_CI_NO == null) WS_CI_NO = "";
        JIBS_tmp_int = WS_CI_NO.length();
        for (int i=0;i<12-JIBS_tmp_int;i++) WS_CI_NO += " ";
        if (WS_ID_NO == null) WS_ID_NO = "";
        JIBS_tmp_int = WS_ID_NO.length();
        for (int i=0;i<70-JIBS_tmp_int;i++) WS_ID_NO += " ";
        WS_ID_NO = WS_ID_NO.substring(0, 9 - 1) + WS_CI_NO.substring(4 - 1, 4 + 3 - 1) + WS_ID_NO.substring(9 + 3 - 1);
        if (WS_CI_NO == null) WS_CI_NO = "";
        JIBS_tmp_int = WS_CI_NO.length();
        for (int i=0;i<12-JIBS_tmp_int;i++) WS_CI_NO += " ";
        if (WS_ID_NO == null) WS_ID_NO = "";
        JIBS_tmp_int = WS_ID_NO.length();
        for (int i=0;i<70-JIBS_tmp_int;i++) WS_ID_NO += " ";
        WS_ID_NO = WS_ID_NO.substring(0, 12 - 1) + WS_CI_NO.substring(12 - 1, 12 + 1 - 1) + WS_ID_NO.substring(12 + 1 - 1);
        WS_RANDOM_NO = 0;
        WS_RANDOM_NO = 999999;
        random = new Random();
        WS_RANDOM_NO = random.nextInt(WS_RANDOM_NO);
        CEP.TRC(SCCGWA, WS_RANDOM_NO);
        if (WS_ID_NO == null) WS_ID_NO = "";
        JIBS_tmp_int = WS_ID_NO.length();
        for (int i=0;i<70-JIBS_tmp_int;i++) WS_ID_NO += " ";
        JIBS_tmp_str[0] = "" + WS_RANDOM_NO;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<6-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        WS_ID_NO = WS_ID_NO.substring(0, 13 - 1) + JIBS_tmp_str[0] + WS_ID_NO.substring(13 + 6 - 1);
        CEP.TRC(SCCGWA, WS_ID_NO);
    }
    public void B050_GEN_CI_NM() throws IOException,SQLException,Exception {
        WS_I = 0;
        WS_FULL_NM = WS_CI_NM_DATA[1-1].WS_CI_NM;
        for (WS_I = 2; WS_I <= 10; WS_I += 1) {
            if (CICOPCJ.DATA[WS_I-1].CI_NO.trim().length() > 0) {
                WS_FULL_NM = WS_FULL_NM;
            }
        }
    }
    public void B060_WRITE_CIRJRL_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRJRL);
        CIRJRL.KEY.HCI_NO = CICOPCJ.DATA[WS_I-1].CI_NO;
        CIRJRL.KEY.JCI_NO = WS_CI_NO;
        CIRJRL.NUM = WS_CNT;
        CIRJRL.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        CIRJRL.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
        CIRJRL.CRT_DT = SCCGWA.COMM_AREA.AC_DATE;
        CIRJRL.UPD_DT = SCCGWA.COMM_AREA.AC_DATE;
        CIRJRL.CRT_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        CIRJRL.UPD_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        T000_WRITE_CITJRL();
    }
    public void B070_WRITE_CIRBAS_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRBAS);
        CIRBAS.KEY.CI_NO = WS_CI_NO;
        CIRBAS.CI_TYP = '1';
        CIRBAS.ID_TYPE = "14000";
        CIRBAS.ID_NO = WS_ID_NO;
        CIRBAS.CI_NM = WS_FULL_NM;
        BAS_CI_NM_LEN = CIRBAS.CI_NM.length();
        CIRBAS.CI_ATTR = '3';
        CIRBAS.STSW = "ZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZERO";
        if (CIRBAS.STSW == null) CIRBAS.STSW = "";
        JIBS_tmp_int = CIRBAS.STSW.length();
        for (int i=0;i<80-JIBS_tmp_int;i++) CIRBAS.STSW += " ";
        CIRBAS.STSW = "1" + CIRBAS.STSW.substring(1);
        CIRBAS.OPN_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        CIRBAS.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        CIRBAS.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
        CIRBAS.CRT_DT = SCCGWA.COMM_AREA.AC_DATE;
        CIRBAS.UPD_DT = SCCGWA.COMM_AREA.AC_DATE;
        CIRBAS.CRT_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        CIRBAS.UPD_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        CIRBAS.OWNER_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        T000_WRITE_CITBAS();
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'A';
        R000_SAVE_HIS_PROC();
        R000_TRANS_DATA_TO_OUTPUT();
    }
    public void B080_OUTPUT_CI_NO() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CICOSORC);
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = "CIO07";
        SCCFMT.DATA_PTR = CICOSORC;
        SCCFMT.DATA_LEN = 423;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void R000_TRANS_DATA_TO_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICOSORC);
        CICOSORC.DATA.CI_NO = CIRBAS.KEY.CI_NO;
        CICOSORC.DATA.CI_TYP = CIRBAS.CI_TYP;
        CICOSORC.DATA.ID_TYPE = CIRBAS.ID_TYPE;
        CICOSORC.DATA.ID_NO = CIRBAS.ID_NO;
        if (CIRBAS.CI_NM == null) CIRBAS.CI_NM = "";
        JIBS_tmp_int = CIRBAS.CI_NM.length();
        for (int i=0;i<252-JIBS_tmp_int;i++) CIRBAS.CI_NM += " ";
        CICOSORC.DATA.CI_NM = CIRBAS.CI_NM.substring(0, BAS_CI_NM_LEN);
        CICOSORC.DATA.CI_ATTR = CIRBAS.CI_ATTR;
        CICOSORC.DATA.CI_STSW = CIRBAS.STSW;
    }
    public void R000_SAVE_HIS_PROC() throws IOException,SQLException,Exception {
        BPCPNHIS.INFO.DATA_FLG = 'Y';
        BPCPNHIS.INFO.FMT_ID = "CIRBAS";
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_CD = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        BPCPNHIS.INFO.MAKER_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCPNHIS.INFO.CI_NO = CIRBAS.KEY.CI_NO;
        BPCPNHIS.INFO.FMT_ID_LEN = 568;
        BPCPNHIS.INFO.NEW_DAT_PT = CIRBAS;
        S000_CALL_BPZPNHIS();
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-REC-NHIS", BPCPNHIS);
    }
    public void S000_CALL_CIZCINO() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-OPEN-CI-NO", CICCINO);
        if (CICCINO.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICCINO.RC);
        }
    }
    public void T000_READ_CITBAS() throws IOException,SQLException,Exception {
        CITBAS_RD = new DBParm();
        CITBAS_RD.TableName = "CITBAS";
        IBS.READ(SCCGWA, CIRBAS, CITBAS_RD);
    }
    public void T000_READ_CITJRL() throws IOException,SQLException,Exception {
        CITJRL_RD = new DBParm();
        CITJRL_RD.TableName = "CITJRL";
        IBS.READ(SCCGWA, CIRJRL, CITJRL_RD);
    }
    public void T000_STARTBR_CITJRL_BY_HCINO() throws IOException,SQLException,Exception {
        CITJRL_BR.rp = new DBParm();
        CITJRL_BR.rp.TableName = "CITJRL";
        CITJRL_BR.rp.eqWhere = "HCI_NO";
        IBS.STARTBR(SCCGWA, CIRJRL, CITJRL_BR);
    }
    public void T000_READNEXT_CITJRL() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, CIRJRL, this, CITJRL_BR);
    }
    public void T000_ENDBR_CITJRL() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, CITJRL_BR);
    }
    public void T000_WRITE_CITJRL() throws IOException,SQLException,Exception {
        CITJRL_RD = new DBParm();
        CITJRL_RD.TableName = "CITJRL";
        IBS.WRITE(SCCGWA, CIRJRL, CITJRL_RD);
    }
    public void T000_WRITE_CITBAS() throws IOException,SQLException,Exception {
        CITBAS_RD = new DBParm();
        CITBAS_RD.TableName = "CITBAS";
        IBS.WRITE(SCCGWA, CIRBAS, CITBAS_RD);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
