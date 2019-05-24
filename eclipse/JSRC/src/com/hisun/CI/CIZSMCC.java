package com.hisun.CI;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.BP.BPCPARMC;
import com.hisun.BP.BPCPNHIS;
import com.hisun.BP.BPCPRMR;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMPAG;
import com.hisun.SC.SCCMSG;

public class CIZSMCC {
    boolean pgmRtn = false;
    short WS_I = 0;
    String WS_ID_NO = " ";
    String WS_MSG_INFO = " ";
    char WS_FATCA_FLG = ' ';
    char WS_EADR_FLG = ' ';
    char WS_BADR_FLG = ' ';
    char WS_OFCAD_FLG = ' ';
    char WS_COTR_FLG = ' ';
    String WS_DEL_CI_NO = " ";
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    CIRBAS CIRBAS = new CIRBAS();
    CIRBAS CIRBASN = new CIRBAS();
    CIRBAS CIRBASO = new CIRBAS();
    CIRCDM CIRCDM = new CIRCDM();
    CIRCDM CIRCDMN = new CIRCDM();
    CIRCDM CIRCDMO = new CIRCDM();
    CIRNAM CIRNAM = new CIRNAM();
    CIRID CIRID = new CIRID();
    CIRID CIRIDN = new CIRID();
    CIRID CIRIDO = new CIRID();
    CIRRISK CIRRISK = new CIRRISK();
    CIRCRS CIRCRS = new CIRCRS();
    CIRRELT CIRRELT = new CIRRELT();
    CIRRREL CIRRREL = new CIRRREL();
    CIRRELN CIRRELN = new CIRRELN();
    CICCINO CICCINO = new CICCINO();
    CICOCINO CICOCINO = new CICOCINO();
    CICUSIZE CICUSIZE = new CICUSIZE();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    CICCGHIS CICCGHIS = new CICCGHIS();
    BPCPARMC BPCPARMC = new BPCPARMC();
    BPCPRMR BPCPRMR = new BPCPRMR();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    CICSMCC CICSMCC;
    public void MP(SCCGWA SCCGWA, CICSMCC CICSMCC) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.CICSMCC = CICSMCC;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "CIZSMCC return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, CICSMCC.RC);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        B020_WRITE_NORMAL_INFO();
        if (pgmRtn) return;
        B030_WRITE_RISK_INFO();
        if (pgmRtn) return;
        B100_WRITE_HISTORY();
        if (pgmRtn) return;
        B120_OUTPUT_CI_NO();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CICSMCC.DATA.RESIDENT);
        CEP.TRC(SCCGWA, CICSMCC.DATA.SUB_TYP);
        CEP.TRC(SCCGWA, CICSMCC.DATA.ORG_TYPE);
        CEP.TRC(SCCGWA, CICSMCC.DATA.ECO);
        CEP.TRC(SCCGWA, "BBB");
        CEP.TRC(SCCGWA, CICSMCC.DATA.HECO);
        CEP.TRC(SCCGWA, "AAAA");
        CEP.TRC(SCCGWA, CICSMCC.DATA.REG_DT);
        CEP.TRC(SCCGWA, CICSMCC.DATA.REG_CCY);
        CEP.TRC(SCCGWA, CICSMCC.DATA.REG_AMT);
        CEP.TRC(SCCGWA, CICSMCC.DATA.BUSN_SCP);
        CEP.TRC(SCCGWA, CICSMCC.DATA.INDUS1);
        CEP.TRC(SCCGWA, CICSMCC.DATA.ENC_CD);
        CEP.TRC(SCCGWA, CICSMCC.DATA.SIZE);
        CEP.TRC(SCCGWA, CICSMCC.DATA.SID_FLG);
        CEP.TRC(SCCGWA, CICSMCC.DATA.HCNTY);
        CEP.TRC(SCCGWA, CICSMCC.DATA.DEP_TYPE);
        CEP.TRC(SCCGWA, CICSMCC.DATA.PROD_TYP);
        CEP.TRC(SCCGWA, CICSMCC.DATA.LST_CITY);
        CEP.TRC(SCCGWA, CICSMCC.DATA.STK_NO);
        CEP.TRC(SCCGWA, CICSMCC.DATA.OPER_INC);
        CEP.TRC(SCCGWA, CICSMCC.DATA.REVENUE);
        CEP.TRC(SCCGWA, CICSMCC.DATA.EMP_NUM);
        CEP.TRC(SCCGWA, CICSMCC.DATA.TOTAL_ASS);
        CEP.TRC(SCCGWA, CICSMCC.DATA.NET_ASS);
        CEP.TRC(SCCGWA, CICSMCC.DATA.CAP_CCY);
        CEP.TRC(SCCGWA, CICSMCC.DATA.CAP_AMT);
        CEP.TRC(SCCGWA, CICSMCC.DATA.PB_AP_NO);
        CEP.TRC(SCCGWA, CICSMCC.DATA.PB_BANK);
        CEP.TRC(SCCGWA, CICSMCC.DATA.PB_AC_NO);
        CEP.TRC(SCCGWA, CICSMCC.DATA.SUP_NM);
        CEP.TRC(SCCGWA, CICSMCC.DATA.SUP_ORNO);
        CEP.TRC(SCCGWA, CICSMCC.DATA.SUP_APNO);
        CEP.TRC(SCCGWA, CICSMCC.DATA.CNT1_NM);
        CEP.TRC(SCCGWA, CICSMCC.DATA.CNT1_MOB);
        CEP.TRC(SCCGWA, CICSMCC.DATA.CNT1_TEL);
        CEP.TRC(SCCGWA, CICSMCC.DATA.CNT2_NM);
        CEP.TRC(SCCGWA, CICSMCC.DATA.CNT2_MOB);
        CEP.TRC(SCCGWA, CICSMCC.DATA.CNT2_TEL);
        CEP.TRC(SCCGWA, CICSMCC.DATA.DIR_NM);
        CEP.TRC(SCCGWA, CICSMCC.DATA.DIR_MOB);
        CEP.TRC(SCCGWA, CICSMCC.DATA.DIR_TEL);
        CEP.TRC(SCCGWA, CICSMCC.DATA.REMARK);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.TL_ID);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AC_DATE);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.BR_DP.TR_BRANCH);
        CEP.TRC(SCCGWA, CICSMCC.DATA.CI_TYP);
        if (CICSMCC.DATA.CI_TYP != '2') {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_INPUT_DATA_ERR, "客户类型不符");
        }
        CEP.TRC(SCCGWA, CICSMCC.DATA.CI_ATTR);
        if (CICSMCC.DATA.CI_ATTR != '1') {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_INPUT_DATA_ERR, "客户性质不符");
        }
        CEP.TRC(SCCGWA, CICSMCC.DATA.SUB_TYP);
        if (CICSMCC.DATA.SUB_TYP.trim().length() == 0) {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_MUST_INPUT, "客户类型细分必须输入");
        }
        CEP.TRC(SCCGWA, CICSMCC.DATA.CAP_AMT);
        CEP.TRC(SCCGWA, CICSMCC.DATA.CAP_CCY);
        if ((CICSMCC.DATA.CAP_AMT != 0 
            && CICSMCC.DATA.CAP_CCY.trim().length() == 0) 
            || (CICSMCC.DATA.CAP_AMT == 0 
            && CICSMCC.DATA.CAP_CCY.trim().length() > 0)) {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_ACTFD_ONE_NOTIN);
        }
        CEP.TRC(SCCGWA, CICSMCC.DATA.ID_TYPE);
        CEP.TRC(SCCGWA, CICSMCC.DATA.ID_NO);
        CEP.TRC(SCCGWA, CICSMCC.DATA.CI_NM);
        if (CICSMCC.DATA.ID_TYPE.trim().length() == 0 
            || CICSMCC.DATA.ID_NO.trim().length() == 0 
            || CICSMCC.DATA.CI_NM.trim().length() == 0) {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_MUST_INPUT, "三要素必须输�?");
        }
        R000_MOD_CHECK_INFO();
        if (pgmRtn) return;
        R000_CHECK_IDNM();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, CICSMCC.DATA.RESIDENT);
        CEP.TRC(SCCGWA, CICSMCC.DATA.HECO);
        CEP.TRC(SCCGWA, CICSMCC.DATA.INDUS1);
        CEP.TRC(SCCGWA, CICSMCC.DATA.SID_FLG);
        CEP.TRC(SCCGWA, CICSMCC.DATA.SIZE);
        CEP.TRC(SCCGWA, CICSMCC.DATA.BUSN_SCP);
        CEP.TRC(SCCGWA, CICSMCC.DATA.REG_DT);
        CEP.TRC(SCCGWA, CICSMCC.DATA.REG_CCY);
        CEP.TRC(SCCGWA, CICSMCC.DATA.REG_AMT);
        CEP.TRC(SCCGWA, CICSMCC.DATA.NED_TYP);
        CEP.TRC(SCCGWA, CICSMCC.DATA.PB_AP_NO);
        CEP.TRC(SCCGWA, CICSMCC.DATA.PB_BANK);
        CEP.TRC(SCCGWA, CICSMCC.DATA.PB_AC_NO);
        CEP.TRC(SCCGWA, CICSMCC.DATA.PL_FLG);
        if (CICSMCC.DATA.RESIDENT == ' ' 
            || CICSMCC.DATA.HECO.trim().length() == 0 
            || CICSMCC.DATA.INDUS1.trim().length() == 0 
            || CICSMCC.DATA.SID_FLG == ' ' 
            || CICSMCC.DATA.BUSN_SCP.trim().length() == 0 
            || CICSMCC.DATA.REG_DT == 0 
            || CICSMCC.DATA.REG_CCY.trim().length() == 0 
            || CICSMCC.DATA.REG_AMT == 0 
            || CICSMCC.DATA.NED_TYP.trim().length() == 0) {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_MUST_INPUT);
        }
        B011_MOD_CHECK_INFO();
        if (pgmRtn) return;
        B012_MOD_CHECK_INFO();
        if (pgmRtn) return;
        B013_MOD_CHECK_INFO();
        if (pgmRtn) return;
        if (CICSMCC.DATA.ID_TYPE.trim().length() > 0) {
            IBS.init(SCCGWA, BPCPARMC);
            IBS.init(SCCGWA, BPCPRMR);
            BPCPRMR.FUNC = ' ';
            BPCPRMR.TYP = "PARMC";
            if (BPCPRMR.CD == null) BPCPRMR.CD = "";
            JIBS_tmp_int = BPCPRMR.CD.length();
            for (int i=0;i<40-JIBS_tmp_int;i++) BPCPRMR.CD += " ";
            BPCPRMR.CD = "CIIDO" + BPCPRMR.CD.substring(5);
            if (BPCPRMR.CD == null) BPCPRMR.CD = "";
            JIBS_tmp_int = BPCPRMR.CD.length();
            for (int i=0;i<40-JIBS_tmp_int;i++) BPCPRMR.CD += " ";
            if (CICSMCC.DATA.ID_TYPE == null) CICSMCC.DATA.ID_TYPE = "";
            JIBS_tmp_int = CICSMCC.DATA.ID_TYPE.length();
            for (int i=0;i<5-JIBS_tmp_int;i++) CICSMCC.DATA.ID_TYPE += " ";
            BPCPRMR.CD = BPCPRMR.CD.substring(0, 6 - 1) + CICSMCC.DATA.ID_TYPE + BPCPRMR.CD.substring(6 + 5 - 1);
            BPCPRMR.DAT_PTR = BPCPARMC;
            S000_CALL_BPZPRMR();
            if (pgmRtn) return;
        }
    }
    public void B011_MOD_CHECK_INFO() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CICSMCC.DATA.RESIDENT);
        CEP.TRC(SCCGWA, CICSMCC.DATA.CI_ENM);
        if (CICSMCC.DATA.RESIDENT != '1') {
            if (CICSMCC.DATA.CI_ENM.trim().length() == 0) {
                WS_MSG_INFO = " ";
                WS_MSG_INFO = "非居民客户，" + "英文名称必输";
                CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_MUST_INPUT, WS_MSG_INFO);
            }
        }
    }
    public void B012_MOD_CHECK_INFO() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CICSMCC.DATA.SCH_DT);
        if (CICSMCC.DATA.SCH_DT == 0) {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_MUST_INPUT, "查册日期必须输入");
        }
        CEP.TRC(SCCGWA, CICSMCC.DATA.HKC_TYP);
        if (CICSMCC.DATA.HKC_TYP.trim().length() == 0) {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_MUST_INPUT, "香港客户分类必须输入");
        }
    }
    public void B013_MOD_CHECK_INFO() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CICSMCC.DATA.INDUS2);
        if (CICSMCC.DATA.INDUS2.trim().length() == 0) {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_MUST_INPUT, "香港行业分类必须输入");
        }
    }
    public void B020_WRITE_NORMAL_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRBAS);
        IBS.init(SCCGWA, CIRBASN);
        IBS.init(SCCGWA, CIRBASO);
        CIRBAS.KEY.CI_NO = CICSMCC.DATA.CI_NO;
        T000_READ_CITBAS_UPD();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_BAS_INF_NOTFND);
        }
        if (!CICSMCC.DATA.ID_TYPE.equalsIgnoreCase(CIRBAS.ID_TYPE) 
            || !CICSMCC.DATA.ID_NO.equalsIgnoreCase(CIRBAS.ID_NO)) {
            WS_ID_NO = " ";
            if (CICSMCC.DATA.ID_NO == null) CICSMCC.DATA.ID_NO = "";
            JIBS_tmp_int = CICSMCC.DATA.ID_NO.length();
            for (int i=0;i<70-JIBS_tmp_int;i++) CICSMCC.DATA.ID_NO += " ";
            if (CICSMCC.DATA.ID_NO == null) CICSMCC.DATA.ID_NO = "";
            JIBS_tmp_int = CICSMCC.DATA.ID_NO.length();
            for (int i=0;i<70-JIBS_tmp_int;i++) CICSMCC.DATA.ID_NO += " ";
            WS_ID_NO = CICSMCC.DATA.ID_NO + "-" + CICSMCC.DATA.ID_NO;
            if (CIRBAS.ID_TYPE.equalsIgnoreCase("20001") 
                && CICSMCC.DATA.ID_TYPE.equalsIgnoreCase("23000") 
                && CIRBAS.ID_NO.equalsIgnoreCase(WS_ID_NO)) {
            } else {
                if (CICSMCC.DATA.ID_TYPE.equalsIgnoreCase("23000")) {
                    IBS.init(SCCGWA, CIRID);
                    CIRID.ID_NO = WS_ID_NO;
                    if (CICSMCC.DATA.ID_NO == null) CICSMCC.DATA.ID_NO = "";
                    JIBS_tmp_int = CICSMCC.DATA.ID_NO.length();
                    for (int i=0;i<70-JIBS_tmp_int;i++) CICSMCC.DATA.ID_NO += " ";
                    CEP.TRC(SCCGWA, CICSMCC.DATA.ID_NO.substring(9 - 1, 9 + 9 - 1));
                    CEP.TRC(SCCGWA, CIRID.ID_NO);
                    T000_READ_CITID_BY_IDNO();
                    if (pgmRtn) return;
                    if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
                        CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_ID_INF_EXIST);
                    }
                }
            }
            IBS.init(SCCGWA, CIRID);
            IBS.init(SCCGWA, CIRIDO);
            IBS.init(SCCGWA, CIRIDN);
            CIRID.KEY.CI_NO = CICSMCC.DATA.CI_NO;
            T000_READ_CITID_UPD_FOR_OPENID();
            if (pgmRtn) return;
            if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
                IBS.CLONE(SCCGWA, CIRID, CIRIDO);
                CIRID.KEY.ID_TYPE = CICSMCC.DATA.ID_TYPE;
                CIRID.ID_NO = CICSMCC.DATA.ID_NO;
                CIRID.REMARK = CICSMCC.DATA.ID_RMK;
                CIRID.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
                CIRID.UPD_DT = SCCGWA.COMM_AREA.AC_DATE;
                CIRID.UPD_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
                T000_REWRITE_CITID();
                if (pgmRtn) return;
                IBS.CLONE(SCCGWA, CIRID, CIRIDN);
                IBS.init(SCCGWA, CICCGHIS);
                CICCGHIS.DATA.TABLE_NM = "CITID";
                CICCGHIS.DATA.OLD_DATA_LEN = 374;
                CICCGHIS.DATA.NEW_DATA_LEN = 374;
                CICCGHIS.DATA.OLD_DATA_PTR = CIRIDO;
                CICCGHIS.DATA.NEW_DATA_PTR = CIRIDN;
                S000_CALL_CIZCGHIS();
                if (pgmRtn) return;
            } else {
                CEP.TRC(SCCGWA, "ID INF NOT FOUND");
                CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_ID_NOTFND);
            }
        }
        if (!CICSMCC.DATA.CI_NM.equalsIgnoreCase(CIRBAS.CI_NM)) {
            IBS.init(SCCGWA, CIRNAM);
            CIRNAM.KEY.CI_NO = CICSMCC.DATA.CI_NO;
            T000_READ_CITNAM_UPD_FOR_OPENNM();
            if (pgmRtn) return;
            if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
                CIRNAM.CI_NM = CICSMCC.DATA.CI_NM;
                NAM_CI_NM_LEN = CIRNAM.CI_NM.length();
                CIRNAM.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
                CIRNAM.UPD_DT = SCCGWA.COMM_AREA.AC_DATE;
                CIRNAM.UPD_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
                T000_REWRITE_CITNAM();
                if (pgmRtn) return;
            } else {
                CEP.TRC(SCCGWA, "NAM INF NOT FOUND");
                CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_NAM_NOTFND);
            }
        }
        IBS.CLONE(SCCGWA, CIRBAS, CIRBASO);
        CEP.TRC(SCCGWA, CICSMCC.DATA.PL_FLG);
        CEP.TRC(SCCGWA, CICSMCC.DATA.PEA_FLG);
        CEP.TRC(SCCGWA, CICSMCC.DATA.GRE_FLG);
        CEP.TRC(SCCGWA, CICSMCC.DATA.CDG_EMP);
        if (CICSMCC.DATA.CDG_EMP == 'Y') {
            if (CIRBAS.IDE_STSW == null) CIRBAS.IDE_STSW = "";
            JIBS_tmp_int = CIRBAS.IDE_STSW.length();
            for (int i=0;i<30-JIBS_tmp_int;i++) CIRBAS.IDE_STSW += " ";
            CIRBAS.IDE_STSW = CIRBAS.IDE_STSW.substring(0, 2 - 1) + "1" + CIRBAS.IDE_STSW.substring(2 + 1 - 1);
        } else {
            if (CIRBAS.IDE_STSW == null) CIRBAS.IDE_STSW = "";
            JIBS_tmp_int = CIRBAS.IDE_STSW.length();
            for (int i=0;i<30-JIBS_tmp_int;i++) CIRBAS.IDE_STSW += " ";
            CIRBAS.IDE_STSW = CIRBAS.IDE_STSW.substring(0, 2 - 1) + "0" + CIRBAS.IDE_STSW.substring(2 + 1 - 1);
        }
        if (CICSMCC.DATA.PL_FLG == 'Y') {
            if (CIRBAS.IDE_STSW == null) CIRBAS.IDE_STSW = "";
            JIBS_tmp_int = CIRBAS.IDE_STSW.length();
            for (int i=0;i<30-JIBS_tmp_int;i++) CIRBAS.IDE_STSW += " ";
            CIRBAS.IDE_STSW = CIRBAS.IDE_STSW.substring(0, 3 - 1) + "1" + CIRBAS.IDE_STSW.substring(3 + 1 - 1);
        } else {
            if (CIRBAS.IDE_STSW == null) CIRBAS.IDE_STSW = "";
            JIBS_tmp_int = CIRBAS.IDE_STSW.length();
            for (int i=0;i<30-JIBS_tmp_int;i++) CIRBAS.IDE_STSW += " ";
            CIRBAS.IDE_STSW = CIRBAS.IDE_STSW.substring(0, 3 - 1) + "0" + CIRBAS.IDE_STSW.substring(3 + 1 - 1);
        }
        if (CICSMCC.DATA.PEA_FLG == 'Y') {
            if (CIRBAS.IDE_STSW == null) CIRBAS.IDE_STSW = "";
            JIBS_tmp_int = CIRBAS.IDE_STSW.length();
            for (int i=0;i<30-JIBS_tmp_int;i++) CIRBAS.IDE_STSW += " ";
            CIRBAS.IDE_STSW = CIRBAS.IDE_STSW.substring(0, 4 - 1) + "1" + CIRBAS.IDE_STSW.substring(4 + 1 - 1);
        } else {
            if (CIRBAS.IDE_STSW == null) CIRBAS.IDE_STSW = "";
            JIBS_tmp_int = CIRBAS.IDE_STSW.length();
            for (int i=0;i<30-JIBS_tmp_int;i++) CIRBAS.IDE_STSW += " ";
            CIRBAS.IDE_STSW = CIRBAS.IDE_STSW.substring(0, 4 - 1) + "0" + CIRBAS.IDE_STSW.substring(4 + 1 - 1);
        }
        if (CICSMCC.DATA.GRE_FLG == 'Y') {
            if (CIRBAS.IDE_STSW == null) CIRBAS.IDE_STSW = "";
            JIBS_tmp_int = CIRBAS.IDE_STSW.length();
            for (int i=0;i<30-JIBS_tmp_int;i++) CIRBAS.IDE_STSW += " ";
            CIRBAS.IDE_STSW = CIRBAS.IDE_STSW.substring(0, 5 - 1) + "1" + CIRBAS.IDE_STSW.substring(5 + 1 - 1);
        } else {
            if (CIRBAS.IDE_STSW == null) CIRBAS.IDE_STSW = "";
            JIBS_tmp_int = CIRBAS.IDE_STSW.length();
            for (int i=0;i<30-JIBS_tmp_int;i++) CIRBAS.IDE_STSW += " ";
            CIRBAS.IDE_STSW = CIRBAS.IDE_STSW.substring(0, 5 - 1) + "0" + CIRBAS.IDE_STSW.substring(5 + 1 - 1);
        }
        CEP.TRC(SCCGWA, CIRBAS.IDE_STSW);
        IBS.init(SCCGWA, CIRRELT);
        CIRRELT.RELT_CNM = CICSMCC.DATA.CI_NM;
        CIRRELT.RELT_ENM = CICSMCC.DATA.CI_ENM;
        T000_STARTBR_CITRELT_BY_NM();
        if (pgmRtn) return;
        T000_READNEXT_CITRELT();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        while (SCCGWA.COMM_AREA.DBIO_FLG != '1') {
            CEP.TRC(SCCGWA, CIRRELT.RELT_TYPE);
            if (CIRRELT.RELT_TYPE.equalsIgnoreCase("01")) {
                if (CIRBAS.IDE_STSW == null) CIRBAS.IDE_STSW = "";
                JIBS_tmp_int = CIRBAS.IDE_STSW.length();
                for (int i=0;i<30-JIBS_tmp_int;i++) CIRBAS.IDE_STSW += " ";
                CIRBAS.IDE_STSW = CIRBAS.IDE_STSW.substring(0, 11 - 1) + "1" + CIRBAS.IDE_STSW.substring(11 + 1 - 1);
            } else if (CIRRELT.RELT_TYPE.equalsIgnoreCase("02")) {
                if (CIRBAS.IDE_STSW == null) CIRBAS.IDE_STSW = "";
                JIBS_tmp_int = CIRBAS.IDE_STSW.length();
                for (int i=0;i<30-JIBS_tmp_int;i++) CIRBAS.IDE_STSW += " ";
                CIRBAS.IDE_STSW = CIRBAS.IDE_STSW.substring(0, 12 - 1) + "1" + CIRBAS.IDE_STSW.substring(12 + 1 - 1);
            } else if (CIRRELT.RELT_TYPE.equalsIgnoreCase("03")) {
                if (CIRBAS.IDE_STSW == null) CIRBAS.IDE_STSW = "";
                JIBS_tmp_int = CIRBAS.IDE_STSW.length();
                for (int i=0;i<30-JIBS_tmp_int;i++) CIRBAS.IDE_STSW += " ";
                CIRBAS.IDE_STSW = CIRBAS.IDE_STSW.substring(0, 13 - 1) + "1" + CIRBAS.IDE_STSW.substring(13 + 1 - 1);
            } else if (CIRRELT.RELT_TYPE.equalsIgnoreCase("04")) {
                if (CIRBAS.IDE_STSW == null) CIRBAS.IDE_STSW = "";
                JIBS_tmp_int = CIRBAS.IDE_STSW.length();
                for (int i=0;i<30-JIBS_tmp_int;i++) CIRBAS.IDE_STSW += " ";
                CIRBAS.IDE_STSW = CIRBAS.IDE_STSW.substring(0, 14 - 1) + "1" + CIRBAS.IDE_STSW.substring(14 + 1 - 1);
            } else if (CIRRELT.RELT_TYPE.equalsIgnoreCase("05")) {
                if (CIRBAS.IDE_STSW == null) CIRBAS.IDE_STSW = "";
                JIBS_tmp_int = CIRBAS.IDE_STSW.length();
                for (int i=0;i<30-JIBS_tmp_int;i++) CIRBAS.IDE_STSW += " ";
                CIRBAS.IDE_STSW = CIRBAS.IDE_STSW.substring(0, 15 - 1) + "1" + CIRBAS.IDE_STSW.substring(15 + 1 - 1);
            } else if (CIRRELT.RELT_TYPE.equalsIgnoreCase("06")) {
                if (CIRBAS.IDE_STSW == null) CIRBAS.IDE_STSW = "";
                JIBS_tmp_int = CIRBAS.IDE_STSW.length();
                for (int i=0;i<30-JIBS_tmp_int;i++) CIRBAS.IDE_STSW += " ";
                CIRBAS.IDE_STSW = CIRBAS.IDE_STSW.substring(0, 16 - 1) + "1" + CIRBAS.IDE_STSW.substring(16 + 1 - 1);
            } else if (CIRRELT.RELT_TYPE.equalsIgnoreCase("07")) {
                if (CIRBAS.IDE_STSW == null) CIRBAS.IDE_STSW = "";
                JIBS_tmp_int = CIRBAS.IDE_STSW.length();
                for (int i=0;i<30-JIBS_tmp_int;i++) CIRBAS.IDE_STSW += " ";
                CIRBAS.IDE_STSW = CIRBAS.IDE_STSW.substring(0, 17 - 1) + "1" + CIRBAS.IDE_STSW.substring(17 + 1 - 1);
            } else if (CIRRELT.RELT_TYPE.equalsIgnoreCase("08")) {
                if (CIRBAS.IDE_STSW == null) CIRBAS.IDE_STSW = "";
                JIBS_tmp_int = CIRBAS.IDE_STSW.length();
                for (int i=0;i<30-JIBS_tmp_int;i++) CIRBAS.IDE_STSW += " ";
                CIRBAS.IDE_STSW = CIRBAS.IDE_STSW.substring(0, 18 - 1) + "1" + CIRBAS.IDE_STSW.substring(18 + 1 - 1);
            } else if (CIRRELT.RELT_TYPE.equalsIgnoreCase("09")) {
                if (CIRBAS.IDE_STSW == null) CIRBAS.IDE_STSW = "";
                JIBS_tmp_int = CIRBAS.IDE_STSW.length();
                for (int i=0;i<30-JIBS_tmp_int;i++) CIRBAS.IDE_STSW += " ";
                CIRBAS.IDE_STSW = CIRBAS.IDE_STSW.substring(0, 19 - 1) + "1" + CIRBAS.IDE_STSW.substring(19 + 1 - 1);
            } else {
            }
            T000_READNEXT_CITRELT();
            if (pgmRtn) return;
        }
        T000_ENDBR_CITRELT();
        if (pgmRtn) return;
        IBS.init(SCCGWA, CIRRREL);
        CIRRREL.REL_CNM = CICSMCC.DATA.CI_NM;
        CIRRREL.REL_ENM = CICSMCC.DATA.CI_ENM;
        T000_STARTBR_CITRREL_BY_NM();
        if (pgmRtn) return;
        T000_READNEXT_CITRREL();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        while (SCCGWA.COMM_AREA.DBIO_FLG != '1') {
            IBS.init(SCCGWA, CIRRELT);
            CIRRELT.KEY.RELT_NO = CIRRREL.RELT_NO;
            T000_READ_CITRELT();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, CIRRELT.RELT_TYPE);
            if (CIRRELT.RELT_TYPE.equalsIgnoreCase("01")) {
                if (CIRBAS.IDE_STSW == null) CIRBAS.IDE_STSW = "";
                JIBS_tmp_int = CIRBAS.IDE_STSW.length();
                for (int i=0;i<30-JIBS_tmp_int;i++) CIRBAS.IDE_STSW += " ";
                CIRBAS.IDE_STSW = CIRBAS.IDE_STSW.substring(0, 11 - 1) + "1" + CIRBAS.IDE_STSW.substring(11 + 1 - 1);
            } else if (CIRRELT.RELT_TYPE.equalsIgnoreCase("02")) {
                if (CIRBAS.IDE_STSW == null) CIRBAS.IDE_STSW = "";
                JIBS_tmp_int = CIRBAS.IDE_STSW.length();
                for (int i=0;i<30-JIBS_tmp_int;i++) CIRBAS.IDE_STSW += " ";
                CIRBAS.IDE_STSW = CIRBAS.IDE_STSW.substring(0, 12 - 1) + "1" + CIRBAS.IDE_STSW.substring(12 + 1 - 1);
            } else if (CIRRELT.RELT_TYPE.equalsIgnoreCase("03")) {
                if (CIRBAS.IDE_STSW == null) CIRBAS.IDE_STSW = "";
                JIBS_tmp_int = CIRBAS.IDE_STSW.length();
                for (int i=0;i<30-JIBS_tmp_int;i++) CIRBAS.IDE_STSW += " ";
                CIRBAS.IDE_STSW = CIRBAS.IDE_STSW.substring(0, 13 - 1) + "1" + CIRBAS.IDE_STSW.substring(13 + 1 - 1);
            } else if (CIRRELT.RELT_TYPE.equalsIgnoreCase("04")) {
                if (CIRBAS.IDE_STSW == null) CIRBAS.IDE_STSW = "";
                JIBS_tmp_int = CIRBAS.IDE_STSW.length();
                for (int i=0;i<30-JIBS_tmp_int;i++) CIRBAS.IDE_STSW += " ";
                CIRBAS.IDE_STSW = CIRBAS.IDE_STSW.substring(0, 14 - 1) + "1" + CIRBAS.IDE_STSW.substring(14 + 1 - 1);
            } else if (CIRRELT.RELT_TYPE.equalsIgnoreCase("05")) {
                if (CIRBAS.IDE_STSW == null) CIRBAS.IDE_STSW = "";
                JIBS_tmp_int = CIRBAS.IDE_STSW.length();
                for (int i=0;i<30-JIBS_tmp_int;i++) CIRBAS.IDE_STSW += " ";
                CIRBAS.IDE_STSW = CIRBAS.IDE_STSW.substring(0, 15 - 1) + "1" + CIRBAS.IDE_STSW.substring(15 + 1 - 1);
            } else if (CIRRELT.RELT_TYPE.equalsIgnoreCase("06")) {
                if (CIRBAS.IDE_STSW == null) CIRBAS.IDE_STSW = "";
                JIBS_tmp_int = CIRBAS.IDE_STSW.length();
                for (int i=0;i<30-JIBS_tmp_int;i++) CIRBAS.IDE_STSW += " ";
                CIRBAS.IDE_STSW = CIRBAS.IDE_STSW.substring(0, 16 - 1) + "1" + CIRBAS.IDE_STSW.substring(16 + 1 - 1);
            } else if (CIRRELT.RELT_TYPE.equalsIgnoreCase("07")) {
                if (CIRBAS.IDE_STSW == null) CIRBAS.IDE_STSW = "";
                JIBS_tmp_int = CIRBAS.IDE_STSW.length();
                for (int i=0;i<30-JIBS_tmp_int;i++) CIRBAS.IDE_STSW += " ";
                CIRBAS.IDE_STSW = CIRBAS.IDE_STSW.substring(0, 17 - 1) + "1" + CIRBAS.IDE_STSW.substring(17 + 1 - 1);
            } else if (CIRRELT.RELT_TYPE.equalsIgnoreCase("08")) {
                if (CIRBAS.IDE_STSW == null) CIRBAS.IDE_STSW = "";
                JIBS_tmp_int = CIRBAS.IDE_STSW.length();
                for (int i=0;i<30-JIBS_tmp_int;i++) CIRBAS.IDE_STSW += " ";
                CIRBAS.IDE_STSW = CIRBAS.IDE_STSW.substring(0, 18 - 1) + "1" + CIRBAS.IDE_STSW.substring(18 + 1 - 1);
            } else if (CIRRELT.RELT_TYPE.equalsIgnoreCase("09")) {
                if (CIRBAS.IDE_STSW == null) CIRBAS.IDE_STSW = "";
                JIBS_tmp_int = CIRBAS.IDE_STSW.length();
                for (int i=0;i<30-JIBS_tmp_int;i++) CIRBAS.IDE_STSW += " ";
                CIRBAS.IDE_STSW = CIRBAS.IDE_STSW.substring(0, 19 - 1) + "1" + CIRBAS.IDE_STSW.substring(19 + 1 - 1);
            } else {
            }
            T000_READNEXT_CITRREL();
            if (pgmRtn) return;
        }
        T000_ENDBR_CITRREL();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, CIRBAS.IDE_STSW);
        CIRBAS.ORGIN_TP = CICSMCC.DATA.ORGIN_TP;
        CIRBAS.ORIGIN = CICSMCC.DATA.ORGIN1;
        CIRBAS.ORIGIN2 = CICSMCC.DATA.ORGIN2;
        CIRBAS.CI_NM = CICSMCC.DATA.CI_NM;
        BAS_CI_NM_LEN = CIRBAS.CI_NM.length();
        CIRBAS.ID_TYPE = CICSMCC.DATA.ID_TYPE;
        CIRBAS.ID_NO = CICSMCC.DATA.ID_NO;
