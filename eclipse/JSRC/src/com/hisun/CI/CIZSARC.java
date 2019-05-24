package com.hisun.CI;

import com.hisun.SC.*;
import com.hisun.BP.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class CIZSARC {
    int BAS_CI_NM_LEN;
    int JIBS_tmp_int;
    DBParm CITBAS_RD;
    DBParm CITRISK_RD;
    DBParm CITCRS_RD;
    brParm CITCRS_BR = new brParm();
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    short WS_I = 0;
    String WS_CI_NO = " ";
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    CIRBAS CIRBAS = new CIRBAS();
    CIRRISK CIRRISK = new CIRRISK();
    CIRRISK CIRRISKN = new CIRRISK();
    CIRCRS CIRCRS = new CIRCRS();
    CICCINO CICCINO = new CICCINO();
    CICOCINO CICOCINO = new CICOCINO();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    CICSARC CICSARC;
    public void MP(SCCGWA SCCGWA, CICSARC CICSARC) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.CICSARC = CICSARC;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "CIZSARC return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, CICSARC.RC);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        B020_ADD_REL_CRS_INFO();
        if (pgmRtn) return;
        B100_WRITE_HISTORY();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CICSARC.DATA.REL_NAME);
        CEP.TRC(SCCGWA, CICSARC.DATA.REL_IDTP);
        CEP.TRC(SCCGWA, CICSARC.DATA.REL_IDNO);
        if (CICSARC.DATA.REL_NAME.trim().length() == 0 
            || CICSARC.DATA.REL_IDTP.trim().length() == 0 
            || CICSARC.DATA.REL_IDNO.trim().length() == 0) {
            CEP.TRC(SCCGWA, "LACK IDNM INF");
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_IDNM_MUST_IPT);
        }
    }
    public void B020_ADD_REL_CRS_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRBAS);
        CIRBAS.CI_NM = CICSARC.DATA.REL_NAME;
        BAS_CI_NM_LEN = CIRBAS.CI_NM.length();
        CIRBAS.ID_TYPE = CICSARC.DATA.REL_IDTP;
        CIRBAS.ID_NO = CICSARC.DATA.REL_IDNO;
        T000_READ_CITBAS_BY_IDNM();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.init(SCCGWA, CICCINO);
            S000_CALL_CIZCINO();
            if (pgmRtn) return;
            WS_CI_NO = CICCINO.DATA.CI_NO;
            IBS.init(SCCGWA, CIRBAS);
            CIRBAS.KEY.CI_NO = WS_CI_NO;
            if (CICSARC.DATA.REL_IDNO == null) CICSARC.DATA.REL_IDNO = "";
            JIBS_tmp_int = CICSARC.DATA.REL_IDNO.length();
            for (int i=0;i<70-JIBS_tmp_int;i++) CICSARC.DATA.REL_IDNO += " ";
            if (CICSARC.DATA.REL_IDNO.substring(0, 1).equalsIgnoreCase("1")) {
                CIRBAS.CI_TYP = '1';
            } else {
                CIRBAS.CI_TYP = '2';
            }
            CIRBAS.CI_ATTR = '2';
            CIRBAS.STSW = "ZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZERO";
            if (CIRBAS.STSW == null) CIRBAS.STSW = "";
            JIBS_tmp_int = CIRBAS.STSW.length();
            for (int i=0;i<80-JIBS_tmp_int;i++) CIRBAS.STSW += " ";
            CIRBAS.STSW = "1" + CIRBAS.STSW.substring(1);
            if (CIRBAS.STSW == null) CIRBAS.STSW = "";
            JIBS_tmp_int = CIRBAS.STSW.length();
            for (int i=0;i<80-JIBS_tmp_int;i++) CIRBAS.STSW += " ";
            CIRBAS.STSW = CIRBAS.STSW.substring(0, 24 - 1) + "1" + CIRBAS.STSW.substring(24 + 1 - 1);
            CIRBAS.CI_NM = CICSARC.DATA.REL_NAME;
            BAS_CI_NM_LEN = CIRBAS.CI_NM.length();
            CIRBAS.ID_TYPE = CICSARC.DATA.REL_IDTP;
            CIRBAS.ID_NO = CICSARC.DATA.REL_IDNO;
            CIRBAS.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
            CIRBAS.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
            CIRBAS.CRT_DT = SCCGWA.COMM_AREA.AC_DATE;
            CIRBAS.UPD_DT = SCCGWA.COMM_AREA.AC_DATE;
            CIRBAS.CRT_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            CIRBAS.UPD_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            T000_WRITE_CITBAS();
            if (pgmRtn) return;
        } else {
            WS_CI_NO = CIRBAS.KEY.CI_NO;
        }
        IBS.init(SCCGWA, CIRRISK);
        CIRRISK.KEY.CI_NO = WS_CI_NO;
        T000_READ_CITRISK_UPD();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CIRRISK.CRS_TYPE = CICSARC.DATA.CRS_TYPE;
            CIRRISK.CRS_DESC = CICSARC.DATA.CRS_DESC;
            CIRRISK.PROOF_DT = CICSARC.DATA.PROOF_DT;
            CIRRISK.PROOF_CHNL = CICSARC.DATA.PROOF_CH;
            CIRRISK.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
            CIRRISK.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
            CIRRISK.CRT_DT = SCCGWA.COMM_AREA.AC_DATE;
            CIRRISK.UPD_DT = SCCGWA.COMM_AREA.AC_DATE;
            CIRRISK.CRT_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            CIRRISK.UPD_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            T000_WRITE_CITRISK();
            if (pgmRtn) return;
        } else {
            CIRRISK.CRS_TYPE = CICSARC.DATA.CRS_TYPE;
            CIRRISK.CRS_DESC = CICSARC.DATA.CRS_DESC;
            CIRRISK.PROOF_DT = CICSARC.DATA.PROOF_DT;
            CIRRISK.PROOF_CHNL = CICSARC.DATA.PROOF_CH;
            CIRRISK.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
            CIRRISK.UPD_DT = SCCGWA.COMM_AREA.AC_DATE;
            CIRRISK.UPD_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            T000_REWRITE_CITRISK();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, CIRCRS);
        CIRCRS.KEY.CI_NO = WS_CI_NO;
        T000_STARTBR_CITCRS();
        if (pgmRtn) return;
        T000_READNEXT_CITCRS();
        if (pgmRtn) return;
        while (SCCGWA.COMM_AREA.DBIO_FLG != '1') {
            T000_DELETE_CITCRS();
            if (pgmRtn) return;
            T000_READNEXT_CITCRS();
            if (pgmRtn) return;
        }
        T000_ENDBR_CITCRS();
        if (pgmRtn) return;
        for (WS_I = 1; WS_I <= 25; WS_I += 1) {
            if (CICSARC.DATA.CRS_AREA[WS_I-1].CRS_ADR.trim().length() > 0) {
                IBS.init(SCCGWA, CIRCRS);
                CIRCRS.KEY.CI_NO = WS_CI_NO;
                CIRCRS.KEY.INFO_TYP = '1';
                CIRCRS.KEY.TAX_ADR = CICSARC.DATA.CRS_AREA[WS_I-1].CRS_ADR;
                CIRCRS.TAX_DIST_NO = CICSARC.DATA.CRS_AREA[WS_I-1].CRS_DSNO;
                CIRCRS.NO_DIST_CODE = CICSARC.DATA.CRS_AREA[WS_I-1].CRS_NDCD;
                CIRCRS.NO_DIST_REASON = CICSARC.DATA.CRS_AREA[WS_I-1].CRS_NDRE;
                CIRCRS.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
                CIRCRS.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
                CIRCRS.CRT_DT = SCCGWA.COMM_AREA.AC_DATE;
                CIRCRS.UPD_DT = SCCGWA.COMM_AREA.AC_DATE;
                CIRCRS.CRT_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
                CIRCRS.UPD_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
                T000_WRITE_CITCRS();
                if (pgmRtn) return;
            }
        }
    }
    public void B100_WRITE_HISTORY() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'A';
        BPCPNHIS.INFO.DATA_FLG = 'Y';
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_CD = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        BPCPNHIS.INFO.MAKER_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCPNHIS.INFO.CI_NO = WS_CI_NO;
        BPCPNHIS.INFO.FMT_ID = "CIRRISK";
        BPCPNHIS.INFO.FMT_ID_LEN = 193;
        BPCPNHIS.INFO.NEW_DAT_PT = CIRRISKN;
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void S000_CALL_CIZCINO() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-OPEN-CI-NO", CICCINO);
        if (CICCINO.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICCINO.RC);
        }
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
    if (!SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF ONL
        IBS.CALLCPN(SCCGWA, "BP-REC-NHIS", BPCPNHIS);
    } //FROM #ENDIF
        if (BPCPNHIS.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, BPCPNHIS.RC);
        }
    }
    public void T000_READ_CITBAS_BY_IDNM() throws IOException,SQLException,Exception {
        CITBAS_RD = new DBParm();
        CITBAS_RD.TableName = "CITBAS";
        CITBAS_RD.eqWhere = "ID_TYPE,ID_NO,CI_NM";
        CITBAS_RD.where = "SUBSTR ( STSW , 3 , 1 ) = '0' "
            + "AND SUBSTR ( STSW , 23 , 1 ) = '0'";
        IBS.READ(SCCGWA, CIRBAS, this, CITBAS_RD);
    }
    public void T000_WRITE_CITBAS() throws IOException,SQLException,Exception {
        CITBAS_RD = new DBParm();
        CITBAS_RD.TableName = "CITBAS";
        IBS.WRITE(SCCGWA, CIRBAS, CITBAS_RD);
    }
    public void T000_READ_CITRISK_UPD() throws IOException,SQLException,Exception {
        CITRISK_RD = new DBParm();
        CITRISK_RD.TableName = "CITRISK";
        CITRISK_RD.upd = true;
        IBS.READ(SCCGWA, CIRRISK, CITRISK_RD);
    }
    public void T000_WRITE_CITRISK() throws IOException,SQLException,Exception {
        CITRISK_RD = new DBParm();
        CITRISK_RD.TableName = "CITRISK";
        CITRISK_RD.errhdl = true;
        IBS.WRITE(SCCGWA, CIRRISK, CITRISK_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_INPUT_DATA_ERR, "CRS税务居住地录入重�?");
        }
    }
    public void T000_REWRITE_CITRISK() throws IOException,SQLException,Exception {
        CITRISK_RD = new DBParm();
        CITRISK_RD.TableName = "CITRISK";
        IBS.REWRITE(SCCGWA, CIRRISK, CITRISK_RD);
    }
    public void T000_WRITE_CITCRS() throws IOException,SQLException,Exception {
        CITCRS_RD = new DBParm();
        CITCRS_RD.TableName = "CITCRS";
        IBS.WRITE(SCCGWA, CIRCRS, CITCRS_RD);
    }
    public void T000_STARTBR_CITCRS() throws IOException,SQLException,Exception {
        CITCRS_BR.rp = new DBParm();
        CITCRS_BR.rp.TableName = "CITCRS";
        CITCRS_BR.rp.eqWhere = "CI_NO";
        CITCRS_BR.rp.upd = true;
        IBS.STARTBR(SCCGWA, CIRCRS, CITCRS_BR);
    }
    public void T000_READNEXT_CITCRS() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, CIRCRS, this, CITCRS_BR);
    }
    public void T000_ENDBR_CITCRS() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, CITCRS_BR);
    }
    public void T000_DELETE_CITCRS() throws IOException,SQLException,Exception {
        CITCRS_RD = new DBParm();
        CITCRS_RD.TableName = "CITCRS";
        IBS.DELETE(SCCGWA, CIRCRS, CITCRS_RD);
    }
    public void B_MPAG() throws IOException,SQLException,Exception {
    if (!SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF ONL
        JIBS_tmp_str[9] = "SCZMPAG";
        Class<?>clazz = Class.forName(JIBS_tmp_str[9].trim());
        Object obj = clazz.newInstance();
        Method m = clazz.getDeclaredMethod("MP",new Class[]{SCCGWA.getClass(), SCCMPAG.getClass()});
        m.invoke(obj, SCCGWA, SCCMPAG);
        if (SCCGWA.COMM_AREA.EXCP_FLG == 'Y') {
            Z_RET();
            if (pgmRtn) return;
        }
    } else { //FROM #ELSE
    } //FROM #ENDIF
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
